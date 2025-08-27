package steps;

import api.dto.store.request.OrderRequestDto;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.random.RandomGenerator;

import static utils.DtoMapper.mapper;

public class StoreApiSteps extends BaseApiSteps {

    private final Logger LOGGER = LoggerFactory.getLogger(StoreApiSteps.class);

    private final String INVENTORY_END_POINT = "/store/inventory";
    private final String ORDER_END_POINT = "/store/order";

    @Step
    public Response getInventory() {
        return sendGetRequest(INVENTORY_END_POINT);
    }

    @Step
    public OrderRequestDto createOrderBody() {
        return OrderRequestDto.builder()
                .id(RandomGenerator.getDefault().nextInt(100))
                .petId(RandomGenerator.getDefault().nextInt(100))
                .quantity(RandomGenerator.getDefault().nextInt(100))
                .shipDate(Instant.now().truncatedTo(ChronoUnit.MILLIS))
                .status("placed")
                .complete(RandomGenerator.getDefault().nextBoolean())
                .build();
    }

    @Step
    @SneakyThrows
    public Response createOrder(OrderRequestDto orderRequestDto) {
        return sendPostRequest(mapper.writeValueAsString(orderRequestDto), ORDER_END_POINT);
    }

    @Step
    public Response getOrder(int orderId) {
        return sendGetRequest(String.format("%s/%d", ORDER_END_POINT, orderId));
    }

    @Step
    public Response deleteOrder(int orderId) {
        return sendDeleteRequest(String.format("%s/%d", ORDER_END_POINT, orderId));
    }
}
