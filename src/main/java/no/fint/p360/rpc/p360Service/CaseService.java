package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.fint.p360.data.exception.CreateCaseException;
import no.p360.model.CaseService.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CaseService extends P360Service {

    public Case getCaseByCaseNumber(String caseNumber) {

        GetCasesArgs getCasesArgs = new GetCasesArgs();
        Parameter__1 parameter = new Parameter__1();
        parameter.setCaseNumber(caseNumber);
        getCasesArgs.setParameter(parameter);

        return getCase(getCasesArgs);
    }

    public Case getCaseBySystemId(String systemId) {

        GetCasesArgs getCasesArgs = new GetCasesArgs();
        Parameter__1 parameter = new Parameter__1();
        parameter.setRecno(Integer.valueOf(systemId));
        getCasesArgs.setParameter(parameter);

        return getCase(getCasesArgs);
    }

    public Case getCaseByExternalId(String externalId) {

        GetCasesArgs getCasesArgs = new GetCasesArgs();
        Parameter__1 parameter = new Parameter__1();
        ExternalId__1 id = new ExternalId__1();
        id.setId(externalId);
        parameter.setExternalId(id);
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

    public String createCase(CreateCaseArgs createCasesArgs) throws CreateCaseException {

        CreateCaseResponse response = call("CaseService/CreateCase", createCasesArgs, CreateCaseResponse.class);

        if(!response.getSuccessful())
            throw new CreateCaseException(response.getErrorDetails());

        return response.getCaseNumber();
    }
}
