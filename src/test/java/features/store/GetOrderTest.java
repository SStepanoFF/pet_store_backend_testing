package features.store;

import api.dto.store.request.OrderRequestDto;
import features.BaseTest;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.WithTag;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import steps.StoreApiAsserts;
import steps.StoreApiSteps;

public class GetOrderTest extends BaseTest {

    @Steps
    private StoreApiSteps storeApiSteps;
    @Steps
    private StoreApiAsserts storeApiAsserts;


    @Test
    @WithTag("store")
    public void getOrderTest(){
        // create order
        OrderRequestDto order = storeApiSteps.createOrderBody();
        storeApiSteps.createOrder(order).then().assertThat().statusCode(200);
        // get order
        Response getOrderResponse = storeApiSteps.getOrder(order.getId());
        storeApiAsserts.validateOrderCommon(getOrderResponse, order.getId());
    }

    @Test
    @WithTag("store")
    public void orderNotFoundTest(){
        Response getOrderResponse = storeApiSteps.getOrder(1000);
        storeApiAsserts.validateCodeResponse(getOrderResponse, getOrderResponse.statusCode(), 1, "error", "Order not found");
    }

    /**
     * I didn't find the option how to get response 400 Invalid ID supplied
     */
    @Test
    @Disabled("App conditions are not satisfied")
    @WithTag("store")
    public void getInvalidOrderIdTest(){
        Response getOrderResponse = storeApiSteps.getOrder(-1);
        storeApiAsserts.validateCodeResponse(getOrderResponse, getOrderResponse.statusCode(), 1, "error", "Invalid ID supplied");
    }
}
