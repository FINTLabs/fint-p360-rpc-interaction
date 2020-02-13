package no.fint.p360.rpc;

import no.fint.model.resource.administrasjon.arkiv.SakResource;
import no.fint.p360.data.exception.*;
import no.fint.p360.rpc.data.noark.sak.SakService;
import no.fint.p360.rpc.data.utilities.FintUtils;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class P360Controller {

    @Autowired
    private SakService sakService;

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

    @GetMapping("sak/casenumber/{year}/{number}")
    public ResponseEntity<SakResource> getSakByCaseNumber(@PathVariable String year, @PathVariable String number) throws GetDocumentException, IllegalCaseNumberFormat {

        String caseNumber = year + "/" + number;

        return ResponseEntity.ok().body(sakService.getSakByCaseNumber(caseNumber));
    }

    @GetMapping("sak/systemid/{systemId}")
    public ResponseEntity<SakResource> getSakBySystemId(@PathVariable String systemId) throws IllegalCaseNumberFormat, GetDocumentException {

        return ResponseEntity.ok().body(sakService.getSakBySystemId(systemId));
    }

    @GetMapping("sak/title")
    public ResponseEntity<List<SakResource>> searchSakByTitle(@RequestParam Map<String, String> query) throws IllegalCaseNumberFormat, GetDocumentException {

        return ResponseEntity.ok().body(sakService.searchSakByTitle(query));
    }

}