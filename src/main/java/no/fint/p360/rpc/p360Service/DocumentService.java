package no.fint.p360.rpc.p360Service;

import lombok.extern.slf4j.Slf4j;
import no.fint.p360.data.exception.CreateDocumentException;
import no.fint.p360.data.exception.GetDocumentException;
import no.p360.model.DocumentService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class DocumentService {

    @Autowired
    private WebClient p360Client;

    @Value("${fint.p360.rpc.authkey}")
    private String auth;

    public void createDocument(CreateDocumentArgs createDocumentArgs) throws CreateDocumentException {
        log.info("Create Document: {}", createDocumentArgs);
        CreateDocumentResponse createDocumentResponse = p360Client.post()
                .uri(String.format("DocumentService/CreateDocument?authkey=%s", auth))
                .bodyValue(createDocumentArgs)
                .retrieve().bodyToMono(CreateDocumentResponse.class).block();
        log.info("Create Document Result: {}", createDocumentResponse);

        if (createDocumentResponse.getSuccessful()) {
            log.info("Documents successfully created");
            return;
        } else throw new CreateDocumentException(createDocumentResponse.getErrorDetails());
    }

    public Document__1 getDocumentBySystemId(String systemId) throws GetDocumentException {
        GetDocumentsArgs getDocumentsArgs = new GetDocumentsArgs();
        Parameter__2 parameter = new Parameter__2();
        parameter.setRecno(Integer.valueOf(systemId));
        parameter.setIncludeRemarks(Boolean.TRUE);
        parameter.setIncludeCustomFields(Boolean.TRUE);
        getDocumentsArgs.setParameter(parameter);

        GetDocumentsResponse documentsResponse = p360Client.post()
                .uri(String.format("DocumentService/GetDocuments?authkey=%s", auth))
                .bodyValue(getDocumentsArgs)
                .retrieve().bodyToMono(GetDocumentsResponse.class).block();

        log.info("DocumentsResult: {}", documentsResponse);
        if (documentsResponse.getSuccessful() && documentsResponse.getDocuments().size()== 1) {
            return documentsResponse.getDocuments().get(0);
        }
        if (documentsResponse.getTotalPageCount() != 1) {
            throw new GetDocumentException("Document could not be found");
        }
        throw new GetDocumentException(documentsResponse.getErrorDetails());
    }
}
