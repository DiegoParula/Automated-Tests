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

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class Test_POST {
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/APIPOST-Test.html");
    static ExtentReports extent;
    ExtentTest test;

    @BeforeAll
    public static void setup() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
    }

    @AfterAll
    public static void teardown() {
        extent.flush();
    }
    //Abrir una nueva cuenta URL:
    //https://parabank.parasoft.com/parabank/services_proxy/bank/createAccount?cust omerId=12545&newAccountType=1&fromAccountId=xxxxx
    @Test
    @Tag("POST")
    public void POST_Abrir_Cuenta() {
        test = extent.createTest("Test Abrir Cuenta Nueva");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Primer Test Post");
        test.log(Status.INFO, "Iniciando Primer Test Post");

        String token = null;
        int customerId = 0;

        /*JsonObject request = new JsonObject();
        request.addProperty("username", "eldiego");
        request.addProperty("password", "123456");


        given()
                .contentType("application/json")
                .body(request)
                .when().get("http://parabank.parasoft.com/parabank/services/bank/login")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();*/

        // Realiza la autenticación y obtiene el token
        String authUrl = "http://parabank.parasoft.com/parabank/services/bank/login";
        String username = "eldiego";
        String password = "123456";

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.body("{\"username\":\"" + username + "\", \"password\":\"" + password + "\"}");

        //Response authResponse = request.get(authUrl);

        Response authResponse = RestAssured.get("http://parabank.parasoft.com/parabank/services/bank/login/"+username+"/"+password);
        // Comprueba que la autenticación fue exitosa
        if (authResponse.statusCode() == 200) {
            customerId = authResponse.xmlPath().getInt("customer.id");
            String cookie = authResponse.getDetailedCookies().getValue("JSESSIONID");
            System.out.println(customerId);
            System.out.println(cookie);

            // System.out.println("Status Code: " + authResponse.getStatusCode());

           // System.out.println("Response Body: " + authResponse.getBody().asString());
           // System.out.println("Response Headers: " + authResponse.getHeaders().toString());
//            token = authResponse.jsonPath().getString("token"); // Ajusta según la respuesta del servidor

            /*// Realiza la solicitud GET autenticada
            Response responseGet = RestAssured.given()
                    .header("Authorization", "Bearer " + token)
                    .get("https://parabank.parasoft.com/parabank/services_proxy/bank/accounts/13766/transactions/month/All/type/All");

            int statusCode = responseGet.statusCode();
            System.out.println(statusCode);
            test.log(Status.INFO, "Status Code: " + statusCode);

            // Loguear el cuerpo de la respuesta
            String responseBody = responseGet.getBody().asString();
            System.out.println(responseBody);
            test.log(Status.INFO, "Response Body: " + responseBody);*/

            test.log(Status.PASS, "Logueado Correctamente");
        } else {
            System.out.println("Error de autenticación");
            test.log(Status.FAIL, "Error de autenticación");
        }




        JsonObject request2 = new JsonObject();
        request2.addProperty("customerId", customerId);
        request2.addProperty("newAccountType", 1);
        request2.addProperty("fromAccountId", 14898);

        given()
                .contentType("application/json")
                //.header("Authorization", "Bearer " + token)
                .body(request)
                .when().post("http://parabank.parasoft.com/parabank/services/bank/createAccount")
                .then()
                .statusCode(200)
                .log().status()
                .log().body();

        test.log(Status.INFO, "Status Code: " + given().get().statusCode());
        System.out.println("Primer Test Post finalizado");
        test.log(Status.PASS, "Primer Test Post finalizado");
    }

    //Descarga de fondos URL: https://parabank.parasoft.com/parabank/services_proxy/bank/transfer?fromAccountId=13566&toAccountId=13677&amount=xxxxx
    @Test
    @Tag("POST")
    public void POST_Descarga_Fondos() {
        test = extent.createTest("Segundo Test POST de la API ReqRes");
        test.log(Status.INFO, "Comienza el Test");

        System.out.println("Iniciando Segundo Test Post");
        test.log(Status.INFO, "Iniciando Segundo Test Post");

        JsonObject request = new JsonObject();
        request.addProperty("name", "SERGIO");
        request.addProperty("job", "PROFESOR");

        given()
                .contentType("application/json")
                .body(request)
                .when().post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("SERGIO"))
                .body("job", equalTo("PROFESOR"))
                .body("createdAt", containsString("2024-06-06"))
                .log().status()
                .log().body();

        System.out.println("Segundo Test Post finalizado");
        test.log(Status.PASS, "Segundo Test Post finalizado");
    }
}