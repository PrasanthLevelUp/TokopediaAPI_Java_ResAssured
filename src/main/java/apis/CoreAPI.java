package apis;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import pojo.Order;

import static utils.ReusableFunctions.getTimeStamp;

public class CoreAPI extends BaseAPI{

    private static final CoreAPI instance = new CoreAPI();

    //private constructor to avoid client applications to use constructor
    private CoreAPI(){}

    public static CoreAPI getInstance(){
        return instance;
    }

    public JSONObject getPayload(String id, String desc, String status, boolean specialOrder) {

        Order order = new Order();
        order.setOrder_id(id);
        order.setOrder_description(desc);
        order.setOrder_status(status);
        order.setLast_updated_timestamp(getTimeStamp());
        order.setSpecial_order(specialOrder);

        JSONObject jsonPayload = new JSONObject(order);
        return jsonPayload;
    }

    public Response createOrder(RequestSpecification request, JSONObject payload) {
        Response response = request.given().body(payload.toString()).log().all().when().post().then().extract().response();
        return response;
    }


}
