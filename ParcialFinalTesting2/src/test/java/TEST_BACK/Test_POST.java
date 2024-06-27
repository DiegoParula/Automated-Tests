package TEST_BACK;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import reportes.ReportFactory;

import java.util.Base64;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Test_POST {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIPOST-Test.html");
    static ExtentReports extent;
    ExtentTest test;
    private static final String USERNAME = "eldiego";
    private static final String PASSWORD = "123456";

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);

        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.config = RestAssured.config().redirect(RestAssured.config().getRedirectConfig().followRedirects(true));
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }
    //Abrir una nueva cuenta URL:
    //https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?cust omerId=12545&newAccountType=1&fromAccountId=xxxxx
    @Test
    @Tag("POST")
    @Tag("ABRI_CUENTA")
    public void POST_Abrir_Cuenta() {
        test = extent.createTest("Test Abrir Cuenta Nueva");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Post");
        test.log(Status.INFO, "Iniciando Primer Test Post");

        given()
                .auth().basic(USERNAME, PASSWORD)
                .contentType("application/x-www-form-urlencoded")
                .formParam("customerId", "13700")
                .formParam("newAccountType", "1")
                .formParam("fromAccountId", "24333")
                .when().post("https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "Primer Test Post finalizado");
    }

    //Descarga de fondos URL: https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx
    @Test
    @Tag("POST")
    @Tag("DESCARGA_FONDOS")
    public void POST_Descarga_Fondos() {
        test = extent.createTest("Segundo Test POST de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Segundo Test Post");
        test.log(Status.INFO, "Iniciando Segundo Test Post");

        given()
                .auth().basic(USERNAME, PASSWORD)
                .contentType("application/x-www-form-urlencoded")
                .formParam("fromAccountId", "14277")
                .formParam("toAccountId", "14600")
                .formParam("amount", "2000")
                .when().post("https://parabank.parasoft.com/parabank/services_proxy/bank/transfer")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        System.out.println("Segundo Test Post finalizado");
        test.log(Status.PASS, "Segundo Test Post finalizado");
    }

}