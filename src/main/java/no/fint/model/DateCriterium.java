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
        "DateName",
        "Operator",
        "DateValue"
})
public class DateCriterium {

    @JsonProperty("DateName")
    public String dateName;
    @JsonProperty("Operator")
    public String operator;
    @JsonProperty("DateValue")//TODO: Use some Date class instead of string??
    public String dateValue;

}