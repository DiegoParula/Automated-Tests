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
        test.log(Status.PASS, "Presiono el botón Register");



        String mensajeExito = registerPage.getMessageSuccess();
        String expectedMessage = "Your account was created successfully. You are now logged in.";
        try {
            assertEquals(expectedMessage, mensajeExito);
            test.log(Status.PASS, "Validación cuenta creada con exito");
        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del registro: esperado: '" + expectedMessage + "' pero fue: '" + mensajeExito + "'");
            throw e;
        } finally {
            test.log(Status.INFO, "Finaliza el test");
        }

    }

/*    @Test
    @Order(2)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Mail Repetido");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "Warning: E-Mail Address is already registered!";
        try {
            RegisterPage registerPage = new RegisterPage(driver, wait);
            registerPage.clickMyAccount();
            test.log(Status.PASS, "Presiono el botón My Account");
            registerPage.clickRegister();
            test.log(Status.PASS, "Presiono el botón Register");
            registerPage.ingresarName("Kiona");
            registerPage.ingresarApellido("Heath");
            registerPage.ingresarMail("nunc.sit@outlook.couk");
            test.log(Status.PASS, "Ingresó mail repetido");
            registerPage.ingresarTelephone("(238) 996-2450");
            registerPage.ingresarContrasenia("1234");
            registerPage.confirmarConytrasenia("1234");
            test.log(Status.PASS, "Ingresó los datos del registro");
            registerPage.clickNoNewsletter();
            registerPage.clickAgree();
            registerPage.clickSubmit();
            test.log(Status.PASS, "Completó el registro y aceptó los términos");
            mss = registerPage.mailRegistrado();

            assertEquals(expectedMessage, mss);
            test.log(Status.PASS, "Validacion de mail repetido exitosa");

        } catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del mail repetido: mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }
*/
    @Test
    @Order(3)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoConfirmacionContrasenia() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Contraseña Confirmación No Coincide");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "Passwords did not match.";
        RegisterPage registerPage = new RegisterPage(driver, wait);
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
        test.log(Status.PASS, "Ingreso contraseñas diferentes");
        test.log(Status.PASS, "Ingreso todos los datos para el registro");
        registerPage.clickSubmitRegister();
        test.log(Status.PASS, "Presiono el botón Register");

        mss = registerPage.getMessageErrorPass();

        try {
            assertTrue(mss.equals(mss));
            test.log(Status.PASS, "Validación contraseña confirmación exitosa.");
        }catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación de contraseña confirmación, mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        } catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
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
