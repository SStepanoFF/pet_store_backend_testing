package features.store;

import api.dto.store.request.OrderRequestDto;
import api.dto.store.response.OrderResponseDto;
import features.BaseTest;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.WithTag;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import steps.StoreApiAsserts;
import steps.StoreApiSteps;
import utils.DtoMapper;

import static org.assertj.core.api.Assertions.assertThat;


public class PlaceOrderTest extends BaseTest {

    @Steps
    private StoreApiSteps storeApiSteps;
    @Steps
    private StoreApiAsserts storeApiAsserts;

    @Test
    @WithTag("store")
    public void placeOrderTest(){
        OrderRequestDto orderRequestDto = storeApiSteps.createOrderBody();
        Response createOrderResponse = storeApiSteps.createOrder(orderRequestDto);
        OrderResponseDto expectedOrder = DtoMapper.map(orderRequestDto, OrderResponseDto.class);
        storeApiAsserts.validateOrder(createOrderResponse, expectedOrder);
    }


    /**
     * It is not possible to get real response code 400 in the system.
     * Test is provided like an example of coverage
     */
    @Test
    @Disabled("App conditions are not satisfied")
    @WithTag("store")
    public void placeInvalidOrderTest(){
        OrderRequestDto orderRequestDto = storeApiSteps.createOrderBody();
        orderRequestDto.setId(-1);
        Response createOrderResponse = storeApiSteps.createOrder(orderRequestDto);
        assertThat(createOrderResponse.statusCode()).isEqualTo(400);
    }
}
