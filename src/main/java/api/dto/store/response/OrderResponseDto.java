package api.dto.store.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponseDto {

    private int id;
    private int petId;
    private int quantity;
    private Instant shipDate;
    private String status;
    private boolean complete;
}
