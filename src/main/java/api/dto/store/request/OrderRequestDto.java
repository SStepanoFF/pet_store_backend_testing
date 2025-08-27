package api.dto.store.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderRequestDto {

    public int id;
    public int petId;
    public int quantity;
    public Instant shipDate;
    public String status;
    public boolean complete;
}
