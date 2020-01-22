package no.fint.p360.rpc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class P360GetCasesRequestBody {

    public P360GetCasesParameter parameter;
}
