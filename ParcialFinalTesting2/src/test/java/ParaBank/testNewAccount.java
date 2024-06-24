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

import static reportes.ReportFactory.captureScreenshot;

@Tag("NEWACCOUNT")
public class testNewAccount{
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Newaccount-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void run() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
        System.out.println("<<< COMIENZAN LOS TEST NEW ACCOUNT >>>");
    }

    @BeforeEach
    public void precondiciones() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        LoginPage loginPage = new LoginPage(driver, wait);
        NewAccount newAccount = new NewAccount(driver, wait);
        loginPage.getUrl("https://parabank.parasoft.com/parabank/index.htm");
        loginPage.insertUsername("eldiego");
        loginPage.insertPassword("123456");
        loginPage.clickLogIn();
    }

    @Test
    @Tag("EXITOSO")
    public void test_Creacion_Cuenta_Exitosa() throws InterruptedException {
        ExtentTest test = extent.createTest("New Account Exitoso");
        test.log(Status.INFO, "Comienza el Test");

        NewAccount newAccount = new NewAccount(driver, wait);;
        try {
            newAccount.openNewAccount();
            test.log(Status.PASS, "Presiono Open New Account");
            newAccount.typeAccount();
            test.log(Status.PASS, "Presiono type of account");
            newAccount.selectAccount();
            test.log(Status.PASS, "Selecciono la cuenta SAVINGS");
            newAccount.account();
            test.log(Status.PASS, "Presiono para seleccionar la cuenta");
            newAccount.selectAccount();
            test.log(Status.PASS, "Selecciono el número de cuenta");
            newAccount.openAccountForm();
            test.log(Status.PASS, "Presiono Open New Account");

            String mss = newAccount.getMessageSuccess();

            test.log(Status.INFO, "Obtengo el mensaje de exito: " + mss);
            System.out.println(mss);


            if (mss.equals("Congratulations, your account is now open.")) {
                test.log(Status.PASS, "Validando mensaje exitoso");
            } else {
                test.log(Status.FAIL, "Fallo validando mensaje exitoso");
                System.out.println("Fallo validando mensaje exitoso");
            }

            test.log(Status.PASS, "Cuenta creada con éxito");
        } catch (Exception error) {
            test.log(Status.FAIL, "FALLO EL TEST DE CREACION DE CUENTA" + error.getMessage());
            captureScreenshot(test, "FAIL_NEWACCOUNT", driver);
        }
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
        System.out.println("<<< FINALIZAN LOS TEST DE NEW ACCOUNT >>>");
    }
}
