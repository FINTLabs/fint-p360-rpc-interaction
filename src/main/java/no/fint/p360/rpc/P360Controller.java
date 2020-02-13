package no.fint.p360.rpc;

import no.fint.model.resource.administrasjon.arkiv.KorrespondansepartResource;
import no.fint.model.resource.administrasjon.arkiv.SakResource;
import no.fint.p360.data.exception.*;
import no.fint.p360.rpc.data.noark.korrespondansepart.KorrespondansepartService;
import no.fint.p360.rpc.data.noark.sak.SakService;
import no.fint.p360.rpc.p360Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Controller
public class P360Controller {

    @Autowired
    private SakService sakService;

    @Autowired
    private KorrespondansepartService korrespondansepartService;

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

    //**************** KorrespondansepartService ********************
    @GetMapping("korrespondansepart/systemid/{systemid}")
    public ResponseEntity<KorrespondansepartResource> getKorrespondansePartBySystemId(@PathVariable int systemid) throws KorrespondansepartNotFound {

        return ResponseEntity.ok().body(korrespondansepartService.getKorrespondansepartBySystemId(systemid));
    }
    @GetMapping("korrespondansepart/fodselsnummer/{fodselsnummer}")
    public ResponseEntity<KorrespondansepartResource> getKorrespondansepartByFodselsnummer(@PathVariable String fodselsnummer) throws KorrespondansepartNotFound {

        return ResponseEntity.ok().body(korrespondansepartService.getKorrespondansepartByFodselsnummer(fodselsnummer));
    }

    @GetMapping("korrespondansepart/organisasjonsnummer/{organisasjonsnummer}")
    public ResponseEntity<KorrespondansepartResource> getKorrespondansepartByOrganisasjonsnummer(@PathVariable String organisasjonsnummer) throws KorrespondansepartNotFound {

        return ResponseEntity.ok().body(korrespondansepartService.getKorrespondansepartByOrganisasjonsnummer(organisasjonsnummer));
    }

    @GetMapping("korrespondansepart/createKorrespondansepart")
    public ResponseEntity<KorrespondansepartResource> createKorrespondansepart(@RequestBody KorrespondansepartResource korrespondansepartResource) throws CreateContactException, CreateEnterpriseException {

        return ResponseEntity.ok().body(korrespondansepartService.createKorrespondansepart(korrespondansepartResource));
    }

    @GetMapping("korrespondansepart/search")
    public ResponseEntity<Stream<KorrespondansepartResource>> search(@RequestBody Map<String, String> queryParams) {

        return ResponseEntity.ok().body(korrespondansepartService.search(queryParams));
    }

}