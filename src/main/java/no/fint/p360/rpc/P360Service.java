package no.fint.p360.rpc;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.GetCasesArgs;
import no.fint.model.P360Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class P360Service {

    @Autowired
    private WebClient p360Client;

    @Value("${fint.p360.rpc.authkey}")
    private String auth;

    public P360Case getCase(int systemId) {

        P360GetCasesParameter getCasesParameter = P360GetCasesParameter.builder().recNo(systemId).build();

        return getCases(getCasesParameter).get(0);
    }

    private List<P360Case> getCases(P360GetCasesParameter getCasesParameter) {

        P360GetCasesResponse response = p360Client.post()
                .uri(String.format("CaseService/GetCases?authkey=%s", auth))
                .bodyValue(P360GetCasesRequestBody.builder().parameter(getCasesParameter).build())
                .retrieve().bodyToMono(P360GetCasesResponse.class).block();

        return response.getCases();
    }

    public List<P360Case> getCases2(GetCasesArgs getCasesArgs) {

        P360GetCasesResponse response = p360Client.post()
                .uri(String.format("CaseService/GetCases?authkey=%s", auth))
                .bodyValue(getCasesArgs)
                .retrieve().bodyToMono(P360GetCasesResponse.class).block();

        return response.getCases();
    }
}
