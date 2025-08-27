package api.dto.store.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CodeResponseDto {

    private int code;
    private String type;
    private String message;
}
