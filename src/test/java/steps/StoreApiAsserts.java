package steps;

import api.dto.store.response.CodeResponseDto;
import api.dto.store.response.InventoryResponseDto;
import api.dto.store.response.OrderResponseDto;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import org.assertj.core.api.SoftAssertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StoreApiAsserts extends ScenarioSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreApiAsserts.class);

    @Step
    public void validateInventory(Response response){
        InventoryResponseDto inventoryResponseDto = response.as(InventoryResponseDto.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).as("Get Identity status code").isEqualTo(200);
        //each time response has a different set of fields, so only 2 constant fields are validated
        softly.assertThat(inventoryResponseDto.getSold()).as("Sold").isPositive();
        softly.assertThat(inventoryResponseDto.getString()).as("String").isPositive();
        softly.assertAll();
    }

    @Step
    public void validateOrder(Response response, OrderResponseDto expectedOrderResponseDto) {
        OrderResponseDto orderResponseDto = response.as(OrderResponseDto.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).as("Status code").isEqualTo(200);
        softly.assertThat(orderResponseDto).isEqualTo(expectedOrderResponseDto);
        softly.assertAll();
    }

    /**
     * Each time the same request returns different values, that's why validating only that fields are not empty
     */
    @Step
    public void validateOrderCommon(Response response, int orderId){
        OrderResponseDto orderResponseDto = response.as(OrderResponseDto.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).as("Status code").isEqualTo(200);
        softly.assertThat(orderResponseDto.getId()).as("Id").isEqualTo(orderId);
        softly.assertThat(orderResponseDto.getPetId()).as("Pet Id").isNotNull();
        softly.assertThat(orderResponseDto.getQuantity()).as("Quantity").isNotNull();
        softly.assertThat(orderResponseDto.getShipDate()).as("Ship Date").isNotNull();
        softly.assertThat(orderResponseDto.getStatus()).as("Status").isNotEmpty();
        softly.assertThat(orderResponseDto.isComplete()).as("Complete").isInstanceOfAny(Boolean.class);
        softly.assertAll();
    }

    public void validateCodeResponse(Response response, int statusCode, int code, String type, String message) {
        CodeResponseDto codeResponseDto = response.as(CodeResponseDto.class);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(response.getStatusCode()).as("Status code").isEqualTo(statusCode);
        softly.assertThat(codeResponseDto.getCode()).as("Code").isEqualTo(code);
        softly.assertThat(codeResponseDto.getMessage()).as("Message").isEqualTo(message);
        softly.assertThat(codeResponseDto.getType()).as("Error").isEqualTo(type);
    }
}
