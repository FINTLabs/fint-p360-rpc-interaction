package no.fint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "DocumentNumber",
        "DocumentTitle"
})
public class Document {

    @JsonProperty("DocumentNumber")
    public String documentNumber;
    @JsonProperty("DocumentTitle")
    public String documentTitle;
}