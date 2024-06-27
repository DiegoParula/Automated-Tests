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

        AccountOverview accountOverview = new AccountOverview(driver, wait);
        String expectedMessage = "*Balance includes deposits that may be subject to holds";
        String actualMessage = "";
        try {

            actualMessage = accountOverview.getMessage();

            test.log(Status.INFO, "Mensaje obtenido: " + actualMessage);

            assertEquals(expectedMessage, actualMessage);
            test.log(Status.PASS, "Validación del mensaje exitoso");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del mensaje. Mensaje esperado: '" + expectedMessage + "', pero fue: '" + actualMessage + "'");
            captureScreenshot(test, "FAIL_RESUMEN_CUENTAS", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Fallo en la ejecución del test: " + e.getMessage());
            captureScreenshot(test, "FAIL_RESUMEN_CUENTAS", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }
    @Test
    @Tag("EXITOSO")
    public void test_Actividad_de_la_cuenta() throws InterruptedException {
        ExtentTest test = extent.createTest("Actividad de la cuenta Exitoso");
        test.log(Status.INFO, "Comienza el Test");

        String txtVisibleAccountOverview = "*Balance includes deposits that may be subject to holds";
        String txtAccountDetails = "Account Details";
        String mss = "";
        String messageAcountDetails = "";
        AccountOverview accountOverview = new AccountOverview(driver, wait);

        try {
            accountOverview.clickOverview();
            test.log(Status.PASS, "Presiono Account Overview");

            mss = accountOverview.getMessage();
            test.log(Status.PASS, "Obtengo el texto: " + mss);

            assertEquals(txtVisibleAccountOverview, mss);
            test.log(Status.PASS, "Validación de texto '" + txtVisibleAccountOverview + "' visible en la pantalla exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo Validación de texto visible en la pantalla: '" + txtVisibleAccountOverview + "'. Mensaje obtenido: '" + mss + "'");
            throw e;
        } catch (Exception ex) {
            test.log(Status.FAIL, "Ocurrió un error al obtener el mensaje de Account Overview: " + ex.getMessage());
            captureScreenshot(test, "FAIL_OBTENER_TEXTO_ACCOUNT_OVERVIEW", driver);
            throw ex;
        }

        try {
            accountOverview.clickAccount();
            test.log(Status.PASS, "Presiono en una cuenta");

            messageAcountDetails = accountOverview.getAccountDetails();
            test.log(Status.PASS, "Obtengo el texto: " + messageAcountDetails);

            assertEquals(txtAccountDetails, messageAcountDetails);
            test.log(Status.PASS, "Validación de texto '" + txtAccountDetails + "' visible en la pantalla exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Fallo Validación de texto visible en la pantalla: '" + txtAccountDetails + "'. Mensaje obtenido: '" + messageAcountDetails + "'");
            throw e;
        } catch (Exception ex) {
            test.log(Status.FAIL, "Ocurrió un error al obtener el mensaje de Account Details: " + ex.getMessage());
            captureScreenshot(test, "FAIL_OBTENER_TEXTO_ACCOUNT_DETAILS", driver);
            throw ex;
        } finally {
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
        System.out.println("<<< FINALIZAN LOS TEST DE ACCOUNT OVERVIEW >>>");
    }
}
