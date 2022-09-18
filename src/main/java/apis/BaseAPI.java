package apis;

import com.aventstack.extentreports.ExtentReports;
import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

import static utils.ExtentConfig.extentSetup;

public class BaseAPI {

    public ExtentReports extent;
    public RequestSpecification request;

    @BeforeSuite
    public void setUp() {
        extent = extentSetup();
        request = RestAssured.given().baseUri(ConfigReader.getBaseURI()).contentType(ContentType.JSON);
    }

    public void user_authenticates() {
        //Authenticating the request using OAuth 2 token
        try {
            request.auth().oauth2(ConfigReader.getToken());
        } catch (Exception e) {
            throw new RuntimeException("User Authenticates Failure");
        }
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();
    }

}
