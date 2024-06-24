package ParaBank;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static reportes.ReportFactory.captureScreenshot;

@Tag("TRANSFER")
public class testTransferFunds {

    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Transfer-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void run() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE TRANSFER FUNDS >>>");
    }

    @BeforeEach
    public void precondiciones() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.insertUsername("eldiego");
        loginPage.insertPassword("123456");
        loginPage.clickLogIn();
    }
    @Test
    @Tag("EXITOSO")
    public void test_Transferir_fondos() throws InterruptedException {
        ExtentTest test = extent.createTest("Tranferencia de fondos exitosa");
        test.log(Status.INFO, "Comienza el Test");
        String txtTitleTransferFunds = "Transfer Funds";
        String txtTransferComplete = "Transfer Complete!";
        TransferFunds transferFunds = new TransferFunds(driver,wait);

        transferFunds.clickTransfer();
        test.log(Status.PASS, "Presiono Transfer Founds");
        String mss = transferFunds.titleTransfer();
        test.log(Status.PASS, "Obtengo el texto: "+ mss);
        transferFunds.insertAmount("45000");
        test.log(Status.PASS, "Ingreso un monto a tranferir");
        transferFunds.clickFromAccount();
        transferFunds.selectFromAccountNumber();
        test.log(Status.PASS, "Selecciono desde que cuenta");
        transferFunds.clickToAccount();
        transferFunds.selectToAccountNumber();
        test.log(Status.PASS, "Selecciono a cual cuenta");
        transferFunds.clickSubmitTransfer();
        test.log(Status.PASS, "Presiono Transfer");
        String messageComplete = transferFunds.transferComplete();
        test.log(Status.PASS, "Obtengo el texto: "+ messageComplete);



        try {
            assertEquals(txtTitleTransferFunds, mss);
            test.log(Status.PASS, "Validación de texto " +txtTitleTransferFunds+" visibles en la pantalla exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo Validación de textos visibles en la pantalla: '" + txtTitleTransferFunds + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_TEXTO_VISIBLE", driver);
            throw e;

        }catch (Exception ex){
            test.log(Status.FAIL, "Ocurrió un error: " + ex.getMessage());
            captureScreenshot(test, "FAIL_TEXTO_VISIBLE", driver);
        }

        try {
            assertEquals(txtTransferComplete, messageComplete);
            test.log(Status.PASS, "Validación de texto " +txtTransferComplete +" visibles en la pantalla exitosa.");
        } catch (AssertionError e) {

            test.log(Status.FAIL, "Fallo Validación de textos visibles en la pantalla: '" + txtTransferComplete + "' pero fue: '" + messageComplete + "'");
            captureScreenshot(test, "FAIL_TEXTO_VISIBLE", driver);
            throw e;

        }catch (Exception ex){
            test.log(Status.FAIL, "Ocurrió un error: " + ex.getMessage());
            captureScreenshot(test, "FAIL_TEXTO_VISIBLE", driver);
        }finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @AfterEach
    public void endTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.close();
    }

    @AfterAll
    public static void finish() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE NEW ACCOUNT >>>");
    }
}
