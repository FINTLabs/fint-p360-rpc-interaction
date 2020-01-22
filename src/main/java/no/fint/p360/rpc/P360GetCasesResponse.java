package no.fint.p360.rpc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class P360GetCasesResponse {

    @JsonProperty("Cases")
    private List<P360Case> cases;
    @JsonProperty("TotalPageCount")
    private int totalPageCount;
    @JsonProperty("TotalCount")
    private int totalCount;
    @JsonProperty("Successful")
    private boolean successful;
    @JsonProperty("ErrorMessage")
    private String errorMessage;
    @JsonProperty("ErrorDetails")
    private String errorDetails;
}
