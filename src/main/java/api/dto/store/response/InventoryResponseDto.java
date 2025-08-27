package api.dto.store.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InventoryResponseDto {

    private int sold;
    @JsonProperty("new")
    private int invNew;
    private int string;
    private int availabla;
    private int avaliable;
    private int pending;
    private int available;
    private int xui;
    private int peric;
}
