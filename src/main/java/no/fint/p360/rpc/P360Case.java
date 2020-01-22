package no.fint.p360.rpc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class P360Case {

    @JsonProperty("Recno")
    private int recNo;
}
