package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.fint.p360.data.exception.CreateDocumentException;
import no.fint.p360.data.exception.GetDocumentException;
import no.p360.model.DocumentService.*;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentService extends P360Service {

    public void createDocument(CreateDocumentArgs createDocumentArgs) throws CreateDocumentException {
        log.info("Create Document: {}", createDocumentArgs);
        CreateDocumentResponse createDocumentResponse = call("DocumentService/CreateDocument", createDocumentArgs, CreateDocumentResponse.class);
        log.info("Create Document Result: {}", createDocumentResponse);

        if (createDocumentResponse.getSuccessful()) {
            log.info("Documents successfully created");
            return;
        }
        throw new CreateDocumentException(createDocumentResponse.getErrorDetails());
    }

    public Document__1 getDocumentBySystemId(String systemId) throws GetDocumentException {
        GetDocumentsArgs getDocumentsArgs = new GetDocumentsArgs();
        Parameter__2 parameter = new Parameter__2();
        parameter.setRecno(Integer.valueOf(systemId));
        parameter.setIncludeRemarks(Boolean.TRUE);
        parameter.setIncludeCustomFields(Boolean.TRUE);
        getDocumentsArgs.setParameter(parameter);

        GetDocumentsResponse getDocumentsResponse = call("DocumentService/GetDocuments", getDocumentsArgs, GetDocumentsResponse.class);

        log.info("DocumentsResult: {}", getDocumentsResponse);
        if (getDocumentsResponse.getSuccessful() && getDocumentsResponse.getDocuments().size() == 1) {
            return getDocumentsResponse.getDocuments().get(0);
        }
        if (getDocumentsResponse.getTotalPageCount() != 1) {
            throw new GetDocumentException("Document could not be found");
        }
        throw new GetDocumentException(getDocumentsResponse.getErrorDetails());
    }
}
