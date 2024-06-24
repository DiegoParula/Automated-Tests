package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import reportes.ReportFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test_GET {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIGET-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);

    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }
    //Registro URL: https://parabank.parasoft.com/parabank/register.htm
    @Test
    @Tag("GET")
    public void GET_Registro_URL() {
        ExtentTest test = extent.createTest("Test Registro URL");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Primer Test Get");
        test.log(Status.INFO, "Iniciando Primer Test Get");
        int statusCode = 0;
        int expectedCode = 200;

        try{
        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/register.htm");
        //System.out.println(responseGet.getBody().asString());
        System.out.println(responseGet.statusCode());
        //System.out.println(responseGet.getHeader("Date"));
        //System.out.println(responseGet.getTime());

        //System.out.println("Primer Test Get finalizado");
        statusCode = responseGet.statusCode();
            test.log(Status.PASS, "Obtengo el status code: " + statusCode);
        assertEquals(expectedCode, statusCode);

        test.log(Status.PASS, "Primer Test Get finalizado exitoso");
        }catch (AssertionError e) {
            test.log(Status.FAIL, "Error en la validación del status code: mensajes esperado: '" + expectedCode + "' pero fue: '" + statusCode + "'");
            throw e;
        }catch (Exception e){
            test.log(Status.FAIL, "Error en la validación del status code:" + e.getMessage());
            throw e;
        }
        finally {
            test.log(Status.PASS, "Finaliza test");
        }
    }
    //Resumen de las cuentas URL: https://parabank.parasoft.com/parabank/overview.htm
    @Test
    @Tag("GET")
    public void GET_overview() {
        ExtentTest test = extent.createTest("Segundo Test GET de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Segundo Test Get");
        test.log(Status.INFO, "Iniciando Segundo Test Get");

       // Response responseGetLogin = RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/login/eldiego/123456");
        //int statusCodeLogin = responseGetLogin.statusCode();
        //System.out.println(statusCodeLogin);
        //System.out.printf(String.valueOf(responseGetLogin.getBody()));
        Response responseGet = RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/customers/13766/accounts");

        int statusCode = responseGet.statusCode();
        System.out.println(statusCode);
        /*JsonPath body = responseGet.jsonPath();
        String nombre_1 = body.getString("data[0].first_name");
        String mail_1 = body.getString("data[0].email");

        assertEquals(nombre_1, "George");
        assertEquals(mail_1, "george.bluth@reqres.in");

        System.out.println("EL CÓDIGO DEL RESPONSE ES: " + statusCode);
        System.out.println("EL NOMBRE DEL PRIMER USUARIO DE LA LISTA ES: " + nombre_1);
        System.out.println("EL MAIL DEL USUARIO" + nombre_1 + " ES: " + mail_1);

        System.out.println("Segundo Test Get finalizado");
        test.log(Status.PASS, "Segundo Test Get finalizado");*/
    }
    //Actividad de la cuenta (cada mes) URL: https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13766/transactions/month/All/type/All
    @Test
    @Tag("GET")
    public void GET_Actividad_Cuenta() {
        ExtentTest test = extent.createTest("Tercer Test GET de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");
        System.out.println("Iniciando Tercer Test Get");
        test.log(Status.INFO, "Iniciando Tercer Test Get");
        Response responseGet = RestAssured.get("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13100/transactions/month/All/type/All");

        int statusCode = responseGet.statusCode();
        System.out.println(statusCode);

        /*given()
                .get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .log().body();

        System.out.println("Tercer Test Get finalizado");
        test.log(Status.PASS, "Tercer Test Get finalizado");*/
    }
}