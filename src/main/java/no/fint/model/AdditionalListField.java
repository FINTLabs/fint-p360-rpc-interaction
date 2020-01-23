package no.fint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Name",
        "Value"
})
public class AdditionalListField {

    @JsonProperty("Name")
    public String name;
    @JsonProperty("Value")
    public Value value;

}