package ParaBank;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static reportes.ReportFactory.captureScreenshot;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Register-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< INICIO TEST DE REGISTRO >>>");

    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        registerPage.setup();
    }

    @Test
    @Order(1)
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void RegistroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");

        RegisterPage registerPage = new RegisterPage(driver, wait);
        String expectedMessage = "Your account was created successfully. You are now logged in.";
        String mensajeExito = null;

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Evan");
            registerPage.insertLastName("Armstrong");
            registerPage.insertAddress("P.O. Box 256, 1634 Libero Road");
            registerPage.insertCity("South Africa");
            registerPage.insertState("Sevastopol City");
            registerPage.insertZip("296332");
            registerPage.insertPhone("(177) 839-3197");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("eldiego");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso los datos para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mensajeExito = registerPage.getMessageSuccess();
            test.log(Status.INFO, "Obtengo el mensaje de éxito: " + mensajeExito);

            assertEquals(expectedMessage, mensajeExito);
            test.log(Status.PASS, "Validación de cuenta creada con éxito.");
            System.out.println(mensajeExito);
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del registro. Mensaje esperado: '" + expectedMessage + "', pero fue: '" + mensajeExito + "'");

            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el test");
        }

    }

    @Test
    @Order(2)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoUsuarioRepetido() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Usuario Repetido");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "This username already exists.";
        try {
            RegisterPage registerPage = new RegisterPage(driver, wait);
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");
            registerPage.insertName("Evan");
            registerPage.insertLastName("Armstrong");
            registerPage.insertAddress("P.O. Box 256, 1634 Libero Road");
            registerPage.insertCity("South Africa");
            registerPage.insertState("Sevastopol City");
            registerPage.insertZip("296332");
            registerPage.insertPhone("(177) 839-3197");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("eldiego");
            test.log(Status.PASS, "Ingreso un username ya registrado");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");
            test.log(Status.PASS, "Ingreso los datos para el registro");
            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Register");
            mss = registerPage.getMessageUserNameError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(expectedMessage, mss);
            test.log(Status.PASS, "Validacion de username repetido exitosa");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del username repetido: mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(3)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoConfirmacionContrasenia() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Contraseña Confirmación No Coincide");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Passwords did not match.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("1234");

            test.log(Status.PASS, "Ingreso contraseñas diferentes y todos los datos para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageErrorPass();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que la contraseña de confirmación no coincide exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de contraseña de confirmación. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_CONTRASENA", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }


    }

    @Test
    @Order(4)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoNombreVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Nombre Vacío");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "First name is required.";
        String mss = "";
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");


            registerPage.insertName("");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto el nombre para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageFirstNameError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que el nombre no puede estar vacío exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de nombre vacío. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_NOMBRE", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(5)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoApellidoVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Apellido Vacío");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Last name is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto el apellido para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageLasteNameError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que el apellido no puede estar vacío exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de apellido vacío. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_APELLIDO", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(6)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoDireccionVacia() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Dirección Vacía");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Address is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto la dirección para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageAdressError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que la dirección no puede estar vacía exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de dirección vacía. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_DIRECCION", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(7)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoCiudadVacia() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Ciudad Vacía");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "City is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            // Dejar el campo de ciudad vacío
            registerPage.insertCity("");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto la ciudad para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageCityError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que la ciudad no puede estar vacía exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de ciudad vacía. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_CIUDAD", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(8)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoEstadoVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Estado Vacío");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "State is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto el estado para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageStateError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que el estado no puede estar vacío exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de estado vacío. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_ESTADO", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(9)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoCodigoPostalVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Código Postal Vacío");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Zip Code is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto el código postal para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageZipError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que el código postal no puede estar vacío exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de código postal vacío. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_ZIP", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(10)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoSSNVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido SSN Vacío");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Social Security Number is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto el SSN para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageSsnError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que el SSN no puede estar vacío exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de SSN vacío. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_SSN", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(12)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoUsernameVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Usuario Vacío");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Username is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto el nombre de usuario para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageUserNameError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que el nombre de usuario no puede estar vacío exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de nombre de usuario vacío. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_USERNAME", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }


    @Test
    @Order(13)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoPasswordVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Contraseña Vacía");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Password is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("");
            registerPage.insertRePassword("123456");

            test.log(Status.PASS, "Ingreso todos los datos excepto la contraseña para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessagePasswordError();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que la contraseña no puede estar vacía exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de contraseña vacía. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_PASSWORD", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }





    @Test
    @Order(14)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoRePasswordVacio() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Confirmación de Contraseña Vacía");
        test.log(Status.INFO, "Comienza el Test");

        String expectedMessage = "Password confirmation is required.";
        String mss = null;
        RegisterPage registerPage = new RegisterPage(driver, wait);

        try {
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");

            registerPage.insertName("Aladdin");
            registerPage.insertLastName("Benjamin");
            registerPage.insertAddress("nunc@yahoo.org");
            registerPage.insertCity("Guanacaste");
            registerPage.insertState("Mexico");
            registerPage.insertZip("675098");
            registerPage.insertPhone("1-763-741-2781");
            registerPage.insertSsn("1234");
            registerPage.insertUsername("Aladdin");
            registerPage.insertPassword("123456");
            registerPage.insertRePassword("");

            test.log(Status.PASS, "Ingreso todos los datos excepto la confirmación de contraseña para el registro");

            registerPage.clickSubmitRegister();
            test.log(Status.PASS, "Presiono el botón Submit Register");

            mss = registerPage.getMessageErrorPass();
            test.log(Status.INFO, "Obtengo el mensaje de error: " + mss);

            assertEquals(mss, expectedMessage);
            test.log(Status.PASS, "Validación de que la confirmación de contraseña no puede estar vacía exitosa.");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de confirmación de contraseña vacía. Mensaje esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            captureScreenshot(test, "FAIL_VALIDACION_REPASSWORD", driver);
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            captureScreenshot(test, "ERROR_GENERAL", driver);
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }
/*
    @Test
    @Order(5)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinLastName() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Apellido Vacío");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "Last Name must be between 1 and 32 characters!";
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        test.log(Status.PASS, "Presionó el botón My Account");
        registerPage.clickRegister();
        test.log(Status.PASS, "Presionó el botón Register");
        registerPage.ingresarName("Shafira");
        registerPage.ingresarApellido("");
        test.log(Status.PASS, "No se ingresó apellido");
        registerPage.ingresarMail("lectus.rutrum@yahoo.couk");
        registerPage.ingresarTelephone("1-581-542-5319");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        test.log(Status.PASS, "Completó el registro y aceptó los términos");

        mss = registerPage.lastNameVacio();

        try {
            assertEquals(expectedMessage, mss);
            test.log(Status.PASS, "Validación en el campo apellido vacío exitoso.");
        }catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del apellido vacío, mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }


    }

    @Test
    @Order(4)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinName() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Nombre Vacío");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "First Name must be between 1 and 32 characters!";
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        test.log(Status.PASS, "Presionó el botón My Account");
        registerPage.clickRegister();
        test.log(Status.PASS, "Presionó el botón Register");
        registerPage.ingresarName("");
        test.log(Status.PASS, "No se ingresó nombre");
        registerPage.ingresarApellido("Bright");
        registerPage.ingresarMail("lectus.rutrum@yahoo.couk");
        registerPage.ingresarTelephone("1-581-542-5319");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        test.log(Status.PASS, "Completó el registro y aceptó los términos");
        mss = registerPage.nameVacio();

        try {
            assertEquals(expectedMessage, mss);
            test.log(Status.PASS, "Validación en el campo nombre vacío exitoso.");
        }catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del nombre vacío, mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }

    }

    @Test
    @Order(6)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinMail() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Mail Vacío");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "E-Mail Address does not appear to be valid!";
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        test.log(Status.PASS, "Presionó el botón My Account");
        registerPage.clickRegister();
        test.log(Status.PASS, "Presionó el botón Register");
        registerPage.ingresarName("Shafira");
        registerPage.ingresarApellido("Bright");
        registerPage.ingresarMail("");
        test.log(Status.PASS, "No se ingresó el mail");
        registerPage.ingresarTelephone("1-581-542-5319");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        test.log(Status.PASS, "Completó el registro y aceptó los términos");

        mss = registerPage.mailVacio();

        try {
            assertEquals(expectedMessage, mss);
            test.log(Status.PASS, "Validación en el campo mail vacío exitoso.");
        }catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del mail vacío, mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }


    }

    @Test
    @Order(7)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinTelephone() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Teléfono Vacío");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "Telephone must be between 3 and 32 characters!";
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        test.log(Status.PASS, "Presionó el botón My Account");
        registerPage.clickRegister();
        test.log(Status.PASS, "Presionó el botón Register");
        registerPage.ingresarName("Shafira");
        registerPage.ingresarApellido("Bright");
        registerPage.ingresarMail("lectus.rutrum@yahoo.couk");
        registerPage.ingresarTelephone("");
        test.log(Status.PASS, "No se ingresó el teléfono");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        test.log(Status.PASS, "Completó el registro y aceptó los términos");

        mss = registerPage.telephoneVacio();

        try {
            assertEquals(expectedMessage, mss);
            test.log(Status.PASS, "Validación en el campo teléfono vacío exitoso.");
        }catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del campo telefóno vacío, mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }


    }*/


    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {

        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
