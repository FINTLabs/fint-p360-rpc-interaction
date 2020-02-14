package no.fint.p360.rpc;

import no.fint.model.resource.administrasjon.arkiv.ArkivdelResource;
import no.fint.model.resource.administrasjon.arkiv.KorrespondansepartResource;
import no.fint.model.resource.administrasjon.arkiv.PartResource;
import no.fint.model.resource.administrasjon.arkiv.SakResource;
import no.fint.model.resource.kultur.kulturminnevern.TilskuddFartoyResource;
import no.fint.p360.data.exception.*;
import no.fint.p360.rpc.data.kulturminne.TilskuddfartoyService;
import no.fint.p360.rpc.data.noark.arkivdel.ArkivdelService;
import no.fint.p360.rpc.data.noark.korrespondansepart.KorrespondansepartService;
import no.fint.p360.rpc.data.noark.part.PartService;
import no.fint.p360.rpc.data.noark.sak.SakService;
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
    private ArkivdelService arkivdelService;

    @Autowired
    private PartService partService;

    @Autowired
    private TilskuddfartoyService tilskuddfartoyService;

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

    //**************** ArkivdelService ********************

    @GetMapping("arkiv/getArkivdel")
    public ResponseEntity<Stream<ArkivdelResource>> getArkivdel() {
        return ResponseEntity.ok().body(arkivdelService.getArkivdel());
    }

    //**************** PartService ********************

    @GetMapping("part/getPartByPartId/{id}")
    public ResponseEntity<PartResource> getPartByPartId(@PathVariable int id) throws PartNotFound {
        return ResponseEntity.ok().body(partService.getPartByPartId(id));
    }

    //**************** TilskuffFartoyService ********************

    @GetMapping("tilskuddFartoy/createTilskuddFartoyCase")
    public ResponseEntity<TilskuddFartoyResource> createTilskuddFartoyCase(@RequestBody TilskuddFartoyResource tilskuddFartoyResource) throws CreateDocumentException, CreateCaseException, NotTilskuddfartoyException, GetTilskuddFartoyNotFoundException, GetDocumentException, IllegalCaseNumberFormat, GetTilskuddFartoyException {
        return ResponseEntity.ok().body(tilskuddfartoyService.createTilskuddFartoyCase(tilskuddFartoyResource));
    }

}