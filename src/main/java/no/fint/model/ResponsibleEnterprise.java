package no.fint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Email",
        "Recno",
        "ExternalId",
        "Referencenumber",
        "Name",
        "Url",
        "Domain"
})
public class ResponsibleEnterprise {

    @JsonProperty("Email")
    public String email;
    @JsonProperty("Recno")
    public Integer recno;
    @JsonProperty("ExternalId")
    public String externalId;
    @JsonProperty("Referencenumber")
    public String referencenumber;
    @JsonProperty("Name")
    public String name;
    @JsonProperty("Url")
    public String url;
    @JsonProperty("Domain")
    public Integer domain;

}