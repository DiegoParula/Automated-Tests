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
        loginPage.getUrl("https://digital-booking-front.digitalhouse.com/login");
    }

    @Test
    @Tag("EXITOSO")
    public void test_LogueoExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.PASS, "Ingreso en el Login de Digital Booking");
        LoginPage loginPage = new LoginPage(driver, wait);
        try {
            loginPage.completarMail("targ5181@gmail.com");
            loginPage.completarContraseña("Admin123.");
            test.log(Status.PASS, "Ingreso todos los datos del Login");
            loginPage.clickIngresar();

            if (loginPage.nombreLogueado().equals("tania rodriguez")) {
                test.log(Status.PASS, "Validar nombre usuario en el login");
            } else {
                test.log(Status.FAIL, "Fallo validación del nombre del usuario");
            }

            test.log(Status.PASS, "Validación de Login Exitoso");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE LOGIN" + error.getMessage());
            captureScreenshot(test, "FAIL_LoginExitoso", driver);
        }
        test.log(Status.INFO, "Finaliza el Test");
    }

    @Test
    @Tag("FALLIDO")
    public void test_LogueoMailVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Fallido por Email Vacio");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.PASS, "Ingreso en el Login de Digital Booking");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.completarMail("");
        loginPage.completarContraseña("Admin123.");
        test.log(Status.PASS, "Ingreso contraseña sin completar el campo email");

        String valida = loginPage.validaMailObligatorio();
        assertTrue(valida.equals("Este campo es obligatorio"));
        test.log(Status.PASS, "Validación de campo de Email obligatorio");
        test.log(Status.INFO, "Finaliza el Test");
    }

    @Test
    @Tag("FALLIDO")
    public void test_LogueoContraseñaVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Fallido Contraseña Vacia");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.PASS, "Ingreso en el Login de Digital Booking");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.completarMail("targ5181@gmail.com");
        loginPage.completarContraseña("");
        test.log(Status.PASS, "Ingreso email sin completar el campo contraseña");
        loginPage.clickIngresar();

        String valida = loginPage.validaPassObligatorio();
        assertTrue(valida.equals("Este campo es obligatorio"));
        test.log(Status.PASS, "Validación de campo de Contraseña obligatoria");
        test.log(Status.INFO, "Finaliza el Test");
    }

    @Test
    @Tag("FALLIDO")
    public void test_LogueoMailInvalido() throws InterruptedException {
        ExtentTest test = extent.createTest("Logueo Fallido Email Invalido");
        test.log(Status.INFO, "Comienza el Test");
        test.log(Status.PASS, "Ingreso en el Login de Digital Booking");
        LoginPage loginPage = new LoginPage(driver, wait);

        loginPage.completarMail("targ5181");
        loginPage.completarContraseña("Admin123.");
        test.log(Status.PASS, "Ingreso todos los datos con email Invalido");

        String valida = loginPage.validaMensajeInvalido();
        assertTrue(valida.equals("El email es inválido"));
        test.log(Status.PASS, "Validación de email Invalido");
        test.log(Status.INFO, "Finaliza el Test");
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
