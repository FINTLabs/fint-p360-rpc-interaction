package no.fint.p360.rpc;

import no.fint.p360.data.exception.CreateCaseException;
import no.fint.p360.data.exception.GetDocumentException;
import no.fint.p360.rpc.p360Service.CaseService;
import no.fint.p360.rpc.p360Service.DocumentService;
import no.p360.model.CaseService.Case;
import no.fint.p360.data.exception.CreateDocumentException;
import no.p360.model.CaseService.CreateCaseArgs;
import no.p360.model.CaseService.GetCasesArgs;
import no.p360.model.DocumentService.CreateDocumentArgs;
import no.p360.model.DocumentService.Document__1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class P360Controller {

    @Autowired
    private CaseService caseService;

    @Autowired
    private DocumentService documentService;

    @GetMapping("sak/systemid/{systemid}")
    public ResponseEntity<Case> getSak(@PathVariable int systemid) {

        return ResponseEntity.ok().body(caseService.getCaseBySystemId(systemid));
    }

    @GetMapping("getcase")
    public ResponseEntity<Case> getCase(@RequestBody GetCasesArgs getCasesArgs) throws Exception {

        return ResponseEntity.ok().body(caseService.getCase(getCasesArgs));
    }

    @GetMapping("getcases")
    public ResponseEntity<List<Case>> getCases(@RequestBody GetCasesArgs getCasesArgs) {

        return ResponseEntity.ok().body(caseService.getCases(getCasesArgs));
    }

    @PostMapping("createcase")
    public ResponseEntity<String> createCase(@RequestBody CreateCaseArgs createCaseArgs) throws CreateCaseException {

        return ResponseEntity.ok().body(caseService.createCase(createCaseArgs));
    }

    @GetMapping("document/systemid/{systemid}")
    public ResponseEntity<Document__1> getDocument(@PathVariable String systemid) throws GetDocumentException {

        return ResponseEntity.ok().body(documentService.getDocumentBySystemId(systemid));
    }
    @GetMapping("document/create")
    public void createDocument(@RequestBody CreateDocumentArgs createDocumentArgs) throws CreateDocumentException {

        documentService.createDocument(createDocumentArgs);
    }
}
