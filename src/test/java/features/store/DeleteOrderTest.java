package features.store;

import api.dto.store.request.OrderRequestDto;
import features.BaseTest;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.WithTag;
import org.junit.jupiter.api.Test;
import steps.StoreApiAsserts;
import steps.StoreApiSteps;

public class DeleteOrderTest extends BaseTest {

    @Steps
    private StoreApiSteps storeApiSteps;
    @Steps
    private StoreApiAsserts storeApiAsserts;


    @Test
    @WithTag("store")
    public void deleteOrderTest(){
        // create order
        OrderRequestDto order = storeApiSteps.createOrderBody();
        storeApiSteps.createOrder(order).then().assertThat().statusCode(200);
        // delete order
        Response getOrderResponse = storeApiSteps.deleteOrder(order.getId());
        storeApiAsserts.validateCodeResponse(getOrderResponse, 200, 1, "unknown", String.valueOf(order.getId()));
    }

    /**
     * This case returns random result. Requires a stable work of endpoint
     */
    @Test
    @WithTag("store")
    public void deleteNotFoundOrderTest(){
        // create order
        OrderRequestDto order = storeApiSteps.createOrderBody();
        storeApiSteps.createOrder(order).then().assertThat().statusCode(200);
        // delete order
        Response deleteOrderResponse = storeApiSteps.deleteOrder(order.getId());
        deleteOrderResponse.then().statusCode(200);
        // try to delete the same order
        Response secondDeleteOrderResponse = storeApiSteps.deleteOrder(order.getId());
        storeApiAsserts.validateCodeResponse(secondDeleteOrderResponse, 404, 1, "unknown", "Order not found");
    }
}
