package testcases;

import apis.BaseAPI;
import apis.CoreAPI;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.Order;
import utils.OrderStatus;

import static utils.ReusableFunctions.randomStringGivenLength;

public class ValidateOrder extends BaseAPI {

    CoreAPI coreAPI;
    ExtentTest test;

    @BeforeClass
    public void classSetup() {
        coreAPI = CoreAPI.getInstance();
        user_authenticates();
    }

    @DataProvider(name = "TestData")
    public Object[][] testData() {
        Object[][] data = {{4, 1, OrderStatus.New.toString(), true}, {15, 2, OrderStatus.New.toString(), false}};
        return data;
    }

    @Test(dataProvider = "TestData")
    public void validOrder(int idLength, int descCount, String status, boolean specialOrder) {
        try {
            test = extent.createTest(new Object() {
            }.getClass().getEnclosingMethod().getName() + descCount);
            JSONObject payload = coreAPI.getPayload(randomStringGivenLength(idLength), "description_" + descCount, status, specialOrder);
            test.log(Status.PASS, "Requested Payload " + payload.toString());
            Response response = coreAPI.createOrder(request, payload);
            test.log(Status.PASS, "Response " + response.getBody().asString());
            Order order = response.getBody().as(Order.class);
            Assert.assertNotNull(order.getOrder_status());
            test.log(Status.PASS, "Order Status Updated");
            Assert.assertNotNull(order.getLast_updated_timestamp());
            test.log(Status.PASS, "Order Last Updated Timestamp");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
            test.log(Status.PASS, "Successful Status code validation " + HttpStatus.SC_OK);
        } catch (Exception e) {
            test.log(Status.FAIL, e.getMessage());
        }
    }

    @DataProvider(name = "invalidOrderStatusTestData")
    public Object[][] invalidOrderStatusTestData() {
        Object[][] data = {{4, 1, "text", true}, {15, 2, "1234", false}};
        return data;
    }

    @Test(dataProvider = "invalidOrderStatusTestData")
    public void inValidOrderStatus(int idLength, int descCount, String status, boolean specialOrder) {
        try {
            test = extent.createTest(new Object() {
            }.getClass().getEnclosingMethod().getName() + descCount);
            JSONObject payload = coreAPI.getPayload(randomStringGivenLength(idLength), "description_" + descCount, status, specialOrder);
            test.log(Status.PASS, "Requested Payload " + payload.toString());
            Response response = coreAPI.createOrder(request, payload);
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_BAD_REQUEST);
            test.log(Status.PASS, "Bad Request" + HttpStatus.SC_BAD_REQUEST);
        } catch (Exception e) {
            test.log(Status.FAIL, e.getMessage());
        }
    }

    @DataProvider(name = "invalidOrderIDTestData")
    public Object[][] invalidOrderIDTestData() {
        Object[][] data = {{100, 1, OrderStatus.New.toString(), true}, {1, 2, OrderStatus.New.toString(), false}};
        return data;
    }

    @Test(dataProvider = "invalidOrderIDTestData")
    public void inValidOrderID(int idLength, int descCount, String status, boolean specialOrder) {
        try {
            test = extent.createTest(new Object() {
            }.getClass().getEnclosingMethod().getName() + descCount);
            JSONObject payload = coreAPI.getPayload(randomStringGivenLength(idLength), "description_" + descCount, status, specialOrder);
            test.log(Status.PASS, "Requested Payload " + payload.toString());
            Response response = coreAPI.createOrder(request, payload);
            Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
            test.log(Status.PASS, "Request Not Found" + HttpStatus.SC_NOT_FOUND);
        } catch (Exception e) {
            test.log(Status.FAIL, e.getMessage());
        }
    }
}
