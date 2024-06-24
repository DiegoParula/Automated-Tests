package ParaBank;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< COMIENZAN LOS TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setup();
        registerPage.getUrl("https://digital-booking-front.digitalhouse.com");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void RegistroExitoso() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickCrearCuenta();

        registerPage.escribirNombre("Sergio");
        registerPage.escribirApellido("Pace");
        registerPage.escribirCorreo("prueba0024579@gmail.com");
        registerPage.escribirContraseña("123456");
        registerPage.repetirContraseña("123456");
        registerPage.clickRegistrarse();
        registerPage.cuentaCreadaGracias().equals("¡Cuenta registrada con éxito!");
        registerPage.cuentaCreadaExito().equals("Te enviamos un email para confirmar tu cuenta");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.clickCrearCuenta();
        registerPage.escribirNombre("Sergio");
        registerPage.escribirApellido("Pace");
        registerPage.escribirCorreo("prueba00004@gmail.com");
        registerPage.escribirContraseña("123456");
        registerPage.repetirContraseña("123456");
        registerPage.clickRegistrarse();

        registerPage.mailRegistrado().equals("Ese email ya se encuentra registrado");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoContraseña() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickCrearCuenta();

        registerPage.escribirNombre("Sergio");
        registerPage.escribirApellido("Pace");
        registerPage.escribirCorreo("prueba00004@gmail.com");
        registerPage.escribirContraseña("123456321");
        registerPage.repetirContraseña("123456123");

        registerPage.clickRegistrarse();

        registerPage.contraseñaNoCoinciden().equals("Las contraseñas deben ser iguales");
    }

    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
