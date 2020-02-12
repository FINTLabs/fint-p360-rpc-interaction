package no.fint.p360.rpc.data.noark.dokument;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.administrasjon.arkiv.*;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.Link;
import no.fint.model.resource.administrasjon.arkiv.*;
import no.fint.p360.data.exception.FileNotFound;
import no.fint.p360.rpc.data.noark.codes.filformat.FilformatResource;
import no.fint.p360.rpc.repository.InternalRepository;
import no.fint.p360.rpc.repository.KodeverkRepository;
import no.p360.model.DocumentService.File;
import no.p360.model.DocumentService.File__1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;

import static no.fint.p360.rpc.data.utilities.FintUtils.optionalValue;
import static no.fint.p360.rpc.data.utilities.P360Utils.applyParameterFromLink;

@Slf4j
@Service
public class DokumentbeskrivelseFactory {
    @Autowired
    private KodeverkRepository kodeverkRepository;

    @Autowired
    private InternalRepository internalRepository;

    public DokumentbeskrivelseResource toFintResource(File__1 file) {
        DokumentbeskrivelseResource dokumentbeskrivelseResource = new DokumentbeskrivelseResource();
        optionalValue(file.getTitle()).ifPresent(dokumentbeskrivelseResource::setTittel);

        DokumentobjektResource dokumentobjektResource = new DokumentobjektResource();
        optionalValue(file.getFormat()).ifPresent(dokumentobjektResource::setFormat);
        dokumentobjektResource.addReferanseDokumentfil(Link.with(Dokumentfil.class, "systemid", file.getRecno().toString()));

        optionalValue(file.getStatusCode())
                .flatMap(kode -> kodeverkRepository
                        .getDokumentStatus()
                        .stream()
                        .filter(it -> StringUtils.equalsIgnoreCase(kode, it.getKode()))
                        .findAny())
                .map(DokumentStatusResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(Link.apply(DokumentStatus.class, "systemid"))
                .ifPresent(dokumentbeskrivelseResource::addDokumentstatus);

        optionalValue(file.getModifiedBy())
                .map(Collections::singletonList)
                .ifPresent(dokumentbeskrivelseResource::setForfatter);
        dokumentbeskrivelseResource.setBeskrivelse(String.format("%s - %s - %s - %s", file.getStatusDescription(), file.getRelationTypeDescription(), file.getAccessCodeDescription(), file.getVersionFormatDescription()));

        optionalValue(file.getNote())
                .filter(StringUtils::isNotBlank)
                .ifPresent(dokumentbeskrivelseResource::setBeskrivelse);

        dokumentbeskrivelseResource.setDokumentobjekt(Collections.singletonList(dokumentobjektResource));

        optionalValue(file.getRelationTypeCode())
                .flatMap(kode -> kodeverkRepository
                        .getTilknyttetRegistreringSom()
                        .stream()
                        .filter(it -> StringUtils.equalsIgnoreCase(kode, it.getKode()))
                        .findAny())
                .map(TilknyttetRegistreringSomResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(Link.apply(TilknyttetRegistreringSom.class, "systemid"))
                .ifPresent(dokumentbeskrivelseResource::addTilknyttetRegistreringSom);

        optionalValue(file.getCategoryCode())
                .flatMap(kode -> kodeverkRepository
                        .getDokumentType()
                        .stream()
                        .filter(it -> StringUtils.equalsIgnoreCase(kode, it.getKode()))
                        .findAny())
                .map(DokumentTypeResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(Link.apply(DokumentType.class, "systemid"))
                .ifPresent(dokumentbeskrivelseResource::addDokumentType);

        optionalValue(file.getVersionFormatCode())
                .flatMap(kode -> kodeverkRepository
                        .getVariantformat()
                        .stream()
                        .filter(it -> StringUtils.equalsIgnoreCase(kode, it.getKode()))
                        .findAny())
                .map(VariantformatResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(Link.apply(Variantformat.class, "systemid"))
                .ifPresent(dokumentobjektResource::addVariantFormat);

        return dokumentbeskrivelseResource;
    }

    public File toP360(DokumentbeskrivelseResource dokumentbeskrivelse, DokumentobjektResource dokumentobjekt) {
        File file = new File();
        file.setTitle(dokumentbeskrivelse.getTittel());

        kodeverkRepository
                .getFilformat()
                .stream()
                .filter(it -> StringUtils.equalsIgnoreCase(it.getKode(), dokumentobjekt.getFormat()))
                .map(FilformatResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .min(Comparator.comparingInt(Integer::parseInt))
                .map(s -> StringUtils.prependIfMissing(s, "recno:"))
                .ifPresent(file::setFormat);

        //createFileParameter.setFormat(objectFactory.createCreateFileParameterFormat(dokumentobjekt.getFormat()));

        applyParameterFromLink(
                dokumentbeskrivelse.getTilknyttetRegistreringSom(),
                file::setRelationType);

        applyParameterFromLink(
                dokumentbeskrivelse.getDokumentType(),
                file::setCategory);

        applyParameterFromLink(
                dokumentbeskrivelse.getDokumentstatus(),
                file::setStatus);

        applyParameterFromLink(
                dokumentobjekt.getVariantFormat(),
                file::setVersionFormat);

        // TODO Map from incoming fields
        //createFileParameter.setNote(objectFactory.createCreateFileParameterNote(dokumentbeskrivelse.getBeskrivelse()));

        if (dokumentbeskrivelse.getSkjerming() != null) {
            applyParameterFromLink(
                    dokumentbeskrivelse.getSkjerming().getTilgangsrestriksjon(),
                    file::setAccessCode);

            // TODO createDocumentParameter.setAccessGroup();
        }

        // TODO 2019-12-09 Large documents, and ability to fetch from both external URIs and P360
        log.info("Dokumentfil: {}", dokumentobjekt.getReferanseDokumentfil());
        byte[] bytes = dokumentobjekt
                .getReferanseDokumentfil()
                .stream()
                .peek(l -> log.info("Link: {}", l))
                .map(Link::getHref)
                .filter(StringUtils::isNotBlank)
                .map(s -> StringUtils.substringAfterLast(s, "/"))
                .filter(internalRepository::exists)
                .map(internalRepository::silentGetFile)
                .map(DokumentfilResource::getData)
                .filter(StringUtils::isNotBlank)
                .map(Base64.getDecoder()::decode)
                .findAny()
                .orElseThrow(() -> new FileNotFound("File not found for " + dokumentbeskrivelse.getTittel()));

        file.setData(new String(bytes));
        return file;
    }
}
