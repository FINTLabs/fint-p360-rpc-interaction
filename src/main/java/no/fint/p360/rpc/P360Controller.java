package no.fint.p360.rpc;

import no.fint.p360.data.exception.*;
import no.fint.p360.rpc.p360Service.CaseService;
import no.fint.p360.rpc.p360Service.ContactService;
import no.fint.p360.rpc.p360Service.DocumentService;
import no.p360.model.CaseService.Case;
import no.p360.model.CaseService.CreateCaseArgs;
import no.p360.model.CaseService.GetCasesArgs;
import no.p360.model.ContactService.ContactPerson;
import no.p360.model.ContactService.Enterprise;
import no.p360.model.ContactService.PrivatePerson;
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
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class P360Controller {

    @Autowired
    private CaseService caseService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ContactService contactService;

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

    @GetMapping("contact/getPrivatePersonByRecno/{recno}")
    public ResponseEntity<PrivatePerson> getPrivatePersonByRecno(@PathVariable int recno){
        return ResponseEntity.ok(contactService.getPrivatePersonByRecno(recno));
    }

    @GetMapping("contact/getPrivatePersonByPersonalId/{id}")
    public ResponseEntity<PrivatePerson> getPrivatePersonByRecno(@PathVariable String id) throws PrivatePersonNotFound {
        return ResponseEntity.ok(contactService.getPrivatePersonByPersonalIdNumber(id));
    }

    @GetMapping("contact/getContactPersonByRecno/{recno}")
    public ResponseEntity<ContactPerson> getContactPersonByRecno(@PathVariable int recno) {
        return ResponseEntity.ok(contactService.getContactPersonByRecno(recno));
    }

    @GetMapping("contact/getEnterpriseByRecno/{recno}")
    public ResponseEntity<Enterprise> getEnterpriseByRecno(@PathVariable int recno) {
        return ResponseEntity.ok(contactService.getEnterpriseByRecno(recno));
    }

    @GetMapping("contact/getEnterpriseByEnterpriseNumber/{enterpriseNumber}")
    public ResponseEntity<Enterprise> getEnterpriseByEnterpriseNumber(@PathVariable String enterpriseNumber) throws EnterpriseNotFound {
        return ResponseEntity.ok(contactService.getEnterpriseByEnterpriseNumber(enterpriseNumber));
    }
    @GetMapping("contact/searchEnterprise")
    public ResponseEntity<Stream<Enterprise>> searchEnterprise(@RequestBody Map<String, String> map){
        return ResponseEntity.ok(contactService.searchEnterprise(map));
    }

    @GetMapping("contact/searchPrivatePerson")
    public ResponseEntity<Stream<PrivatePerson>> searchPrivatePerson(@RequestBody Map<String, String> map){
        return ResponseEntity.ok(contactService.searchPrivatePerson(map));
    }
    @GetMapping("contact/searchContactPerson")
    public ResponseEntity<Stream<ContactPerson>> searchContactPerson(@RequestBody Map<String, String> map){
        return ResponseEntity.ok(contactService.searchContactPerson(map));
    }
}
