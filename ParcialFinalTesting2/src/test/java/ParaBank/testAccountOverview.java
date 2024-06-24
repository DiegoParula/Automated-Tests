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

@Tag("ACCOUNTOVERVIEW")
public class testAccountOverview {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Overview-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void run() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE ACCOUNT OVERVIEW >>>");
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
    public void test_TextoVisibleExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Resumen de las cuentas");
        test.log(Status.INFO, "Comienza el Test");
        AccountOverview accountOverview = new AccountOverview(driver,wait);

        try {

            String mss = accountOverview.getMessage();

            test.log(Status.INFO, "Obtengo el mensaje: " + mss);
            System.out.println(mss);


            if (mss.equals("*Balance dsfdsfincludes deposits that may be subject to holds")) {
                test.log(Status.PASS, "Validando mensaje exitoso");
            } else {
                test.log(Status.FAIL, "Fallo validando mensaje exitoso");
                System.out.println("Fallo validando mensaje exitoso");
            }

            //test.log(Status.PASS, "Texto Visible");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE TEXTO VISIBLE" + error.getMessage());
            captureScreenshot(test, "FAIL_ACCOUNTOVERVIEW_TEXTVISIBLE", driver);
        }
        test.log(Status.INFO, "Finaliza el Test");
    }
    @Test
    @Tag("EXITOSO")
    public void test_Actividad_de_la_cuenta() throws InterruptedException {
        ExtentTest test = extent.createTest("Actividad de la cuenta Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        String txtVisibleAccountOverview = "*Balance includes deposits that may be subject to holds";
        String txtAccountDetails = "Account Details";
        AccountOverview accountOverview = new AccountOverview(driver,wait);

        accountOverview.clickOverview();
        test.log(Status.PASS, "Presiono Account Overview");
        String mss = accountOverview.getMessage();
        test.log(Status.PASS, "Obtengo el texto: "+ mss);
        accountOverview.clickAccount();
        test.log(Status.PASS, "Presiono en una cuenta");
        String messageAcountDetails = accountOverview.getAccountDetails();
        test.log(Status.PASS, "Obtengo el texto: "+ messageAcountDetails);
        accountOverview.clickActivityPeriod();
        accountOverview.clickGetActivityPeriodAll();
        test.log(Status.PASS, "Presiono Activity Period y selecciono all");
        accountOverview.clickType();
        accountOverview.getTypeAll();
        test.log(Status.PASS, "Presiono Type y selecciono all");
        accountOverview.clickSubmitGo();


        try {
            assertEquals(txtVisibleAccountOverview, mss);
            test.log(Status.PASS, "Validación de texto " +txtVisibleAccountOverview+" visibles en la pantalla exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo Validación de textos visibles en la pantalla: '" + txtVisibleAccountOverview + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_TEXTO_VISIBLE", driver);
            throw e;

        }catch (Exception ex){
            test.log(Status.FAIL, "Ocurrió un error: " + ex.getMessage());
            captureScreenshot(test, "FAIL_TEXTO_VISIBLE", driver);
        }

        try {
            assertEquals(txtAccountDetails, messageAcountDetails);
            test.log(Status.PASS, "Validación de texto " +txtAccountDetails +" visibles en la pantalla exitosa.");
        } catch (AssertionError e) {

            test.log(Status.FAIL, "Fallo Validación de textos visibles en la pantalla: '" + txtAccountDetails + "' pero fue: '" + messageAcountDetails + "'");
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
