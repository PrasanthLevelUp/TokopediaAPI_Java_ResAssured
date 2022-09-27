package apis;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import pojo.Order;

import static utils.Constant.ORDER_PATH;
import static utils.ReusableFunctions.getTimeStamp;

public class CoreAPI extends BaseAPI {

    private static final CoreAPI instance = new CoreAPI();

    //private constructor to avoid client applications to use constructor
    private CoreAPI() {
    }

    public static CoreAPI getInstance() {
        return instance;
    }

    public JSONObject getPayload(String id, String desc, String status, boolean specialOrder) {
        JSONObject jsonPayload;

        try {
            Order order = new Order();
            order.setOrder_id(id);
            order.setOrder_description(desc);
            order.setOrder_status(status);
            order.setLast_updated_timestamp(getTimeStamp());
            order.setSpecial_order(specialOrder);
            jsonPayload = new JSONObject(order);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return jsonPayload;
    }

    public Response createOrder(RequestSpecification request, JSONObject payload) {
        Response response;
        try {
            response = request
                    .body(payload.toString())
                    .when().
                    post(ORDER_PATH).
                    then()
                    .extract().response();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return response;
    }


}
