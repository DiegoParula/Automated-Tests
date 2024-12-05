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
import static org.junit.jupiter.api.Assertions.assertTrue;
import static reportes.ReportFactory.captureScreenshot;

@Tag("LOGIN")
public class testLogin {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Login-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void run() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST DE LOGIN >>>");
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
    }

    @Test
    @Tag("EXITOSO")
    public void test_LogueoExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Exitoso");
        test.log(Status.INFO, "Comienza el Test");

        LoginPage loginPage = new LoginPage(driver, wait);
        String expectedTitle = "Accounts Overview";
        String actualTitle = "";

        try {
            loginPage.insertUsername("eldiego");
            loginPage.insertPassword("123456");
            test.log(Status.PASS, "Ingreso todos los datos del Login");

            loginPage.clickLogIn();
            test.log(Status.PASS, "Presiono el botón Log In");

            actualTitle = loginPage.getTitleAccountOverview();
            test.log(Status.INFO, "Obtengo el título de la página: " + actualTitle);

            assertEquals(expectedTitle, actualTitle);
            test.log(Status.PASS, "Validación del título luego del login exitoso");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del título. Título esperado: '" + expectedTitle + "', pero fue: '" + actualTitle + "'");
            captureScreenshot(test, "FAIL_VALIDACION_TITULO", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Fallo el test de login: " + e.getMessage());
            captureScreenshot(test, "FAIL_LOGIN_EXITO", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Tag("FALLIDO")
    public void test_LogueoUserNameVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Fallido por USERNAME Vacio");
        test.log(Status.INFO, "Comienza el Test");

        LoginPage loginPage = new LoginPage(driver, wait);
        String expectedMessage = "Please enter a username and password.";
        String actualMessage = "";

        try {
            test.log(Status.PASS, "Ingreso en el Login");

            loginPage.insertUsername("");
            loginPage.insertPassword("Admin123.");
            test.log(Status.PASS, "Ingreso contraseña sin completar el campo username");

            loginPage.clickLogIn();
            test.log(Status.PASS, "Presiono el botón Log In");

            actualMessage = loginPage.getErrorMessage();
            test.log(Status.INFO, "Obtengo el mensaje de validación: " + actualMessage);

            assertEquals(expectedMessage, actualMessage);
            test.log(Status.PASS, "Validación de campo de username obligatorio exitosa");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del campo de username obligatorio. Mensaje esperado: '" + expectedMessage + "', pero fue: '" + actualMessage + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Fallo el test de logueo: " + e.getMessage());
            captureScreenshot(test, "FAIL_LOGUEO_USERNAME_VACIO", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Tag("FALLIDO")
    public void test_LogueoContraseniaVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Fallido por PASSWORD Vacio");
        test.log(Status.INFO, "Comienza el Test");

        LoginPage loginPage = new LoginPage(driver, wait);
        String expectedMessage = "Please enter a username and password.";
        String actualMessage = "";

        try {
            test.log(Status.PASS, "Ingreso en el Login");

            loginPage.insertUsername("eldiego");
            loginPage.insertPassword("");
            test.log(Status.PASS, "Ingreso username sin completar el campo password");

            loginPage.clickLogIn();
            test.log(Status.PASS, "Presiono el botón Log In");

            actualMessage = loginPage.getErrorMessage();
            test.log(Status.INFO, "Obtengo el mensaje de validación: " + actualMessage);

            assertEquals(expectedMessage, actualMessage);
            test.log(Status.PASS, "Validación de campo de password obligatorio exitosa");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del campo de password obligatorio. Mensaje esperado: '" + expectedMessage + "', pero fue: '" + actualMessage + "'");
            captureScreenshot(test, "FAIL_VALIDACION_PASSWORD", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Fallo el test de logueo: " + e.getMessage());
            captureScreenshot(test, "FAIL_LOGUEO_PASSWORD_VACIO", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Tag("FALLIDO")//The username and password could not be verified.
    public void test_LogueoUsuarioInvalido() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Fallido por Usuario No Registrado");
        test.log(Status.INFO, "Comienza el Test");

        LoginPage loginPage = new LoginPage(driver, wait);
        String expectedMessage = "The username and password could not be verified.";
        String actualMessage = "";

        try {
            test.log(Status.PASS, "Ingreso en el Login");

            loginPage.insertUsername("usuarioNoRegistrado");
            loginPage.insertPassword("contraseña");
            test.log(Status.PASS, "Ingreso usuario y contraseña no registrados");

            loginPage.clickLogIn();
            test.log(Status.PASS, "Presiono el botón Log In");

            actualMessage = loginPage.getErrorMessage();
            test.log(Status.INFO, "Obtengo el mensaje de validación: " + actualMessage);

            assertEquals(expectedMessage, actualMessage);
            test.log(Status.PASS, "Validación de logueo fallido por usuario no registrado exitosa");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de logueo fallido por usuario no registrado. Mensaje esperado: '" + expectedMessage + "', pero fue: '" + actualMessage + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Fallo el test de logueo: " + e.getMessage());
            captureScreenshot(test, "FAIL_LOGUEO_USUARIO_NO_REGISTRADO", driver);
            throw e;
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
        System.out.println("<<< FINALIZAN LOS TEST DE LOGIN >>>");
    }
}
