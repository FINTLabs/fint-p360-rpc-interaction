package no.fint.p360.rpc;

import no.fint.model.administrasjon.arkiv.Partsinformasjon;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.Link;
import no.fint.model.resource.administrasjon.arkiv.*;
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

import java.util.*;
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
    public ResponseEntity<TilskuddFartoyResource> createTilskuddFartoyCase() throws CreateDocumentException, CreateCaseException, NotTilskuddfartoyException, GetTilskuddFartoyNotFoundException, GetDocumentException, IllegalCaseNumberFormat, GetTilskuddFartoyException {
        TilskuddFartoyResource tilskuddFartoyResource = new TilskuddFartoyResource();
        tilskuddFartoyResource.setFartoyNavn("FINT fartoy");
        tilskuddFartoyResource.setKallesignal("FINT kallesignal");
        tilskuddFartoyResource.setKulturminneId("12345");
        tilskuddFartoyResource.setTittel("FINT tittel");
        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi("9988776646"); // Needs to change every new createTilskuddFartoyCase call.
        tilskuddFartoyResource.setSoknadsnummer(identifikator);

        Map<String, List<Link>> links = new LinkedHashMap<>();

        ArrayList<Link> link = new ArrayList<>();
        link.add(new Link("www.administrativEnhet.fint/1"));
        links.put("administrativEnhet", link);

        ArrayList<Link> link1 = new ArrayList<>();
        link1.add(new Link("www.saksstatus.fint/B"));
        links.put("saksstatus", link1);

        ArrayList<PartsinformasjonResource> partsinformasjonResources = new ArrayList<>();
        PartsinformasjonResource partsinformasjonResource = new PartsinformasjonResource();
        Map<String, List<Link>> links2 = new LinkedHashMap<>();
        ArrayList<Link> link2 = new ArrayList<>();
        link2.add(new Link("www.part.fint/12"));
        links2.put("part", link2);
        partsinformasjonResource.setLinks(links2);
        tilskuddFartoyResource.setPart(partsinformasjonResources);


        ArrayList<MerknadResource> merknadResources = new ArrayList<>();
        MerknadResource merknadResource = new MerknadResource();
        merknadResource.setMerknadstekst("Fint merknadstekst");
        merknadResources.add(merknadResource);
        Map<String, List<Link>> links3 = new LinkedHashMap<>();
        ArrayList<Link> link3 = new ArrayList<>();
        link3.add(new Link("www.remark.fint/MS"));
        links3.put("merknadstype", link3);
        merknadResource.setLinks(links3);

        tilskuddFartoyResource.setMerknad(merknadResources);

        tilskuddFartoyResource.setLinks(links);

        List<JournalpostResource> journalposts = new ArrayList<>();
        JournalpostResource journalpost = new JournalpostResource();

        journalpost.setAntallVedlegg(0L);
        journalpost.setJournalAr("2020");
        journalpost.setJournalPostnummer(1234L);
        journalpost.setJournalSekvensnummer(1L);
        journalpost.setBeskrivelse("FINT-Journalpost");
        journalpost.setTittel("FINT-Tittel Journalpost");
        journalpost.setOffentligTittel("FINT Offentlig tittel");
        journalpost.addJournalposttype(new Link("www.category.fint/112"));
        journalpost.addJournalstatus(new Link("www.journalstatus.fint/J"));


        journalposts.add(journalpost);
        tilskuddFartoyResource.setJournalpost(journalposts);


        return ResponseEntity.ok().body(tilskuddfartoyService.createTilskuddFartoyCase(tilskuddFartoyResource));
    }
}