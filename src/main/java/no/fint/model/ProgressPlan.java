package no.fint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Recno",
        "Description",
        "WorkunitID"
})
public class ProgressPlan {

    @JsonProperty("Recno")
    public String recno;
    @JsonProperty("Description")
    public String description;
    @JsonProperty("WorkunitID")
    public String workunitID;

}
