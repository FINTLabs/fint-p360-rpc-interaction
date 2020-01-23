package no.fint.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ArchiveCode",
        "ArchiveType"
})
public class ArchiveCode {

    @JsonProperty("ArchiveCode")
    public String archiveCode;
    @JsonProperty("ArchiveType")
    public String archiveType;
}