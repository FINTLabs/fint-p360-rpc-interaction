package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.p360.model.CaseService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@Slf4j
public class CaseService {

    @Autowired
    private WebClient p360Client;

    @Value("${fint.p360.rpc.authkey}")
    private String auth;

    public Case getCaseBySystemId(int systemId) {

        GetCasesArgs getCasesArgs = new GetCasesArgs();
        Parameter__1 parameter = new Parameter__1();
        parameter.setRecno(systemId);
        getCasesArgs.setParameter(parameter);

        return getCase(getCasesArgs);
    }

    public Case getCase(GetCasesArgs getCasesArgs) {

        List<Case> caseResult = getCases(getCasesArgs);

        // TODO: Handle result is not 1 case

        return caseResult.get(0);
    }

    public List<Case> getCases(GetCasesArgs getCasesArgs) {

        GetCasesResponse response = p360Client.post()
                .uri(String.format("CaseService/GetCases?authkey=%s", auth))
                .bodyValue(getCasesArgs)
                .retrieve().bodyToMono(GetCasesResponse.class).block();

        return response.getCases();
    }
}
