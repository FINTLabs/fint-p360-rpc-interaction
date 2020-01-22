package no.fint.p360.rpc;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class P360Service {

    @Autowired
    private WebClient p360Client;

    public P360Case getCase(int systemId) {

        P360GetCasesParameter getCasesParameter = P360GetCasesParameter.builder().recNo(systemId).build();

        return getCases(getCasesParameter).get(0);
    }

    public List<P360Case> getCases(P360GetCasesParameter getCasesParameter) {

        P360GetCasesResponse response = p360Client.post()
                .uri("CaseService/GetCases?authkey=") // TODO: Fetch authkey from config.yaml
                .bodyValue(P360GetCasesRequestBody.builder().parameter(getCasesParameter).build())
                .retrieve().bodyToMono(P360GetCasesResponse.class).block();

        return response.getCases();
    }
}
