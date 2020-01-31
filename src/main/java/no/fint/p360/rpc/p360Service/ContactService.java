package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.fint.p360.data.exception.PrivatePersonNotFound;
import no.p360.model.ContactService.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContactService extends P360Service {

    public PrivatePerson getPrivatePersonByRecno(int recno) {
        GetPrivatePersonsArgs getPrivatePersonsArgs = new GetPrivatePersonsArgs();
        Parameter__2 parameter = new Parameter__2();
        parameter.setRecno(recno);
        parameter.setIncludeCustomFields(true);
        getPrivatePersonsArgs.setParameter(parameter);
        GetPrivatePersonsResponse getPrivatePersonsResponse = call("ContactService/GetPrivatePersons", getPrivatePersonsArgs, GetPrivatePersonsResponse.class);
        log.info("PrivatePersonsResult: {}", getPrivatePersonsResponse);
        if (getPrivatePersonsResponse.getSuccessful() && getPrivatePersonsResponse.getTotalPageCount() == 1){
            return getPrivatePersonsResponse.getPrivatePersons().get(0);
        }
        return null;
    }

    public PrivatePerson getPrivatePersonByPersonalIdNumber(String personalIdNumber) throws PrivatePersonNotFound {
        GetPrivatePersonsArgs getPrivatePersonsArgs = new GetPrivatePersonsArgs();
        Parameter__2 parameter = new Parameter__2();
        parameter.setIncludeCustomFields(true);
        parameter.setPersonalIdNumber(personalIdNumber);
        getPrivatePersonsArgs.setParameter(parameter);

        GetPrivatePersonsResponse getPrivatePersonsResponse = call("ContactService/GetPrivatePersons", getPrivatePersonsArgs, GetPrivatePersonsResponse.class);
        log.info("PrivatePersonsResult: {}", getPrivatePersonsResponse);

        if (getPrivatePersonsResponse.getSuccessful() && getPrivatePersonsResponse.getTotalPageCount() == 1) {
            return getPrivatePersonsResponse.getPrivatePersons().get(0);
        }

        throw new PrivatePersonNotFound(getPrivatePersonsResponse.getErrorMessage());

    }

    public ContactPerson getContactPersonByRecno(int recNo) {
        GetContactPersonsArgs getContactPersonsArgs = new GetContactPersonsArgs();
        Parameter parameter = new Parameter();
        parameter.setIncludeCustomFields(true);
        parameter.setRecno(recNo);
        getContactPersonsArgs.setParameter(parameter);

        GetContactPersonsResponse getContactPersonsResponse = call("ContactService/GetContactPersons", getContactPersonsArgs, GetContactPersonsResponse.class);
        log.info("ContactPersonsResult: {}", getContactPersonsResponse);

        if (getContactPersonsResponse.getSuccessful() && getContactPersonsResponse.getTotalPageCount() == 1) {
            return getContactPersonsResponse.getContactPersons().get(0);
        }
        return null;
    }
/*
    public GetEnterprisesResponse getEnterpriseByRecno(int recNo) {
    }

    public GetEnterprisesResponse getEnterpriseByEnterpriseNumber(String enterpriseNumber) throws EnterpriseNotFound {
    }

    public Stream<GetEnterprisesResponse> searchEnterprise(Map<String, String> queryParams) {
    }

    public Stream<GetPrivatePersonsResponse> searchPrivatePerson(Map<String, String> queryParams) {
    }

    public Stream<GetContactPersonsResponse> searchContactPerson(Map<String, String> queryParams) {
    }

    public Integer createPrivatePerson(SynchronizePrivatePersonArgs privatePerson) throws CreateContactException {
    }

    public Integer createEnterprise(SynchronizeEnterpriseArgs enterprise) throws CreateEnterpriseException {
    }*/
}
