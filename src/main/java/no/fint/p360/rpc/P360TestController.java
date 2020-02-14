package no.fint.p360.rpc;

import no.fint.p360.data.exception.*;
import no.fint.p360.rpc.p360Service.*;
import no.p360.model.AccessGroupService.GetAccessGroupsArgs;
import no.p360.model.AccessGroupService.GetAccessGroupsResponse;
import no.p360.model.CaseService.Case;
import no.p360.model.CaseService.CreateCaseArgs;
import no.p360.model.CaseService.GetCasesArgs;
import no.p360.model.ContactService.*;
import no.p360.model.DocumentService.CreateDocumentArgs;
import no.p360.model.DocumentService.Document__1;
import no.p360.model.FileService.File;
import no.p360.model.SupportService.CodeTableRow;
import no.p360.model.SupportService.GetCodeTableRowsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class P360TestController {


    @Autowired
    private CaseService caseService;

    @Autowired
    private DocumentService documentService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private SupportService supportService;

    @Autowired
    private AccessGroupService accessGroupService;

    @Autowired
    private FileService fileService;

    @GetMapping("case/casenumber/{year}/{number}")
    public ResponseEntity<Case> getCaseByCaseNumber(@PathVariable String year, @PathVariable String number) {

        String caseNumber = year + "/" + number;

        return ResponseEntity.ok().body(caseService.getCaseByCaseNumber(caseNumber));
    }

    @GetMapping("case/systemid/{systemId}")
    public ResponseEntity<Case> getCaseBySystemId(@PathVariable String systemId) {

        return ResponseEntity.ok().body(caseService.getCaseBySystemId(systemId));
    }

    @GetMapping("case/externalid/{externalId}")
    public ResponseEntity<Case> getCaseByExternalId(@PathVariable String externalId) {

        return ResponseEntity.ok().body(caseService.getCaseByExternalId(externalId));
    }

    @GetMapping("case/title/{title}")
    public ResponseEntity<List<Case>> getCasesByTitle(@PathVariable String title, @RequestParam(required = false) String maxReturnedCases) {

        return ResponseEntity.ok().body(caseService.getCasesByTitle(title, maxReturnedCases));
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
    @PostMapping("contact/createPrivatePerson")
    public ResponseEntity<Integer> createPrivatePerson(@RequestBody SynchronizePrivatePersonArgs synchronizePrivatePersonArgs) throws CreateContactException {
        return ResponseEntity.ok(contactService.createPrivatePerson(synchronizePrivatePersonArgs));
    }
    @PostMapping("contact/createEnterprise")
    public ResponseEntity<Integer> createEnterprise(@RequestBody SynchronizeEnterpriseArgs synchronizeEnterpriseArgs) throws CreateEnterpriseException {
        return ResponseEntity.ok(contactService.createEnterprise(synchronizeEnterpriseArgs));
    }

    @GetMapping("support/getCodeTableRows")
    public ResponseEntity<GetCodeTableRowsResponse> getCodeTableRows(@RequestBody CodeTableCode code){
        return ResponseEntity.ok(supportService.getCodeTable(code.getCode()));
    }

    @GetMapping("support/getCodeTableRowsAsStream")
    public ResponseEntity<Stream<CodeTableRow>> getCodeTableRowsAsStream(@RequestBody CodeTableCode code){
        return ResponseEntity.ok(supportService.getCodeTableRowResultStream(code.getCode()));
    }

    @GetMapping("accessgroup/getAccessGroups")
    public ResponseEntity<GetAccessGroupsResponse> getAccessGroups(@RequestBody GetAccessGroupsArgs args){
        return ResponseEntity.ok(accessGroupService.getAccessGroups(args));
    }
    @GetMapping("file/getFileByRecno/{recno}")
    public ResponseEntity<File> getFileByRecno(@PathVariable String recno){
        return ResponseEntity.ok(fileService.getFileByRecNo(recno));
    }
}