package apis;

import com.aventstack.extentreports.ExtentReports;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import helper.ConfigReader;

import static helper.ExtentConfig.extentSetup;

public class BaseAPI {

    public ExtentReports extent;
    public RequestSpecification request;

    @BeforeSuite
    public void setUp() {
        extent = extentSetup();
        request = RestAssured.given().baseUri(ConfigReader.getBaseURI()).contentType(ContentType.JSON);
        this.user_authenticates();
    }

    public void user_authenticates() {
        //Authenticating the request using OAuth 2 token
        try {
            request.auth().oauth2(this.getToken());
        } catch (Exception e) {
            throw new RuntimeException("User Authenticates Failure");
        }
    }

    public String getToken() {
        String accessToken = null;
        try {
            Response response = request.given()
                    .baseUri("http://localhost:8088/")
                    .auth().preemptive().basic("rest-assured", "password")
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("grant_type", "password")
                    .formParam("username", "onlyfullstack")
                    .formParam("password", "secret")
                    .when()
                    .post("/oauth/token")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response();

            JSONObject jsonObject = new JSONObject(response.getBody().asString());
            accessToken = jsonObject.get("access_token").toString();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return accessToken;
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
    }

}
