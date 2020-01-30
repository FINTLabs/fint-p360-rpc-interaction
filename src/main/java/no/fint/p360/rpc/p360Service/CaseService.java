package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.p360.model.CaseService.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CaseService extends P360Service {

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

        GetCasesResponse response = call("CaseService/GetCases", getCasesArgs, GetCasesResponse.class);

        return response.getCases();
    }

    public String createCase(CreateCaseArgs createCasesArgs) {

        CreateCaseResponse response = call("CaseService/CreateCase", createCasesArgs, CreateCaseResponse.class);

        return response.getCaseNumber();
    }
}
