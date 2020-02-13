package no.fint.p360.rpc.data.noark.korrespondansepart;

import no.fint.model.felles.kompleksedatatyper.Kontaktinformasjon;
import no.fint.model.felles.kompleksedatatyper.Personnavn;
import no.fint.model.resource.administrasjon.arkiv.KorrespondansepartResource;
import no.fint.model.resource.felles.kompleksedatatyper.AdresseResource;
import no.fint.p360.rpc.data.utilities.FintUtils;
import no.p360.model.ContactService.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static java.util.Optional.ofNullable;
import static no.fint.p360.rpc.data.utilities.FintUtils.*;

@SuppressWarnings("Duplicates")
@Service
public class KorrespondansepartFactory {

    public KorrespondansepartResource toFintResource(PrivatePerson result) {

        if (result == null) {
            return null;
        }

        KorrespondansepartResource korrespondansepartResource = new KorrespondansepartResource();
        korrespondansepartResource.setAdresse(FintUtils.createAdresse(result));
        korrespondansepartResource.setKontaktinformasjon(FintUtils.createKontaktinformasjon(result));
        korrespondansepartResource.setKorrespondansepartNavn(FintUtils.getFullNameString(result));
        korrespondansepartResource.setSystemId(createIdentifikator(result.getRecno().toString()));
        optionalValue(result.getPersonalIdNumber())
                .filter(StringUtils::isNotBlank)
                .map(FintUtils::createIdentifikator)
                .ifPresent(korrespondansepartResource::setFodselsnummer);

        return korrespondansepartResource;
    }

    public KorrespondansepartResource toFintResource(ContactPerson result) {

        if (result == null) {
            return null;
        }

        KorrespondansepartResource korrespondansepartResource = new KorrespondansepartResource();
        korrespondansepartResource.setAdresse(FintUtils.createAdresse(result));
        korrespondansepartResource.setKontaktinformasjon(FintUtils.createKontaktinformasjon(result));
        korrespondansepartResource.setKorrespondansepartNavn(FintUtils.getFullNameString(result));
        korrespondansepartResource.setSystemId(createIdentifikator(result.getRecno().toString()));

        return korrespondansepartResource;
    }

    public KorrespondansepartResource toFintResource(Enterprise result) {

        if (result == null) {
            return null;
        }

        KorrespondansepartResource korrespondansepartResource = new KorrespondansepartResource();
        korrespondansepartResource.setAdresse(FintUtils.createAdresse(result));
        korrespondansepartResource.setKontaktinformasjon(FintUtils.createKontaktinformasjon(result));
        korrespondansepartResource.setKorrespondansepartNavn(result.getName());
        korrespondansepartResource.setKontaktperson(FintUtils.getKontaktpersonString(result));
        korrespondansepartResource.setSystemId(createIdentifikator(result.getRecno().toString()));
        optionalValue(result.getEnterpriseNumber())
                .filter(StringUtils::isNotBlank)
                .map(FintUtils::createIdentifikator)
                .ifPresent(korrespondansepartResource::setOrganisasjonsnummer);

        return korrespondansepartResource;
    }

    public SynchronizePrivatePersonArgs toPrivatePerson(KorrespondansepartResource korrespondansepartResource) {
        SynchronizePrivatePersonArgs synchronizePrivatePersonArgs = new SynchronizePrivatePersonArgs();
        Parameter__5 parameter = new Parameter__5();
        Personnavn personnavn = parsePersonnavn(korrespondansepartResource.getKorrespondansepartNavn());
        parameter.setFirstName(personnavn.getFornavn());
        parameter.setLastName(personnavn.getEtternavn());
        parameter.setPersonalIdNumber(
                        korrespondansepartResource.getFodselsnummer().getIdentifikatorverdi());

        ofNullable(korrespondansepartResource.getKontaktinformasjon())
                .map(Kontaktinformasjon::getEpostadresse)
                .ifPresent(parameter::setEmail);

        ofNullable(korrespondansepartResource.getKontaktinformasjon())
                .map(Kontaktinformasjon::getMobiltelefonnummer)
                .ifPresent(parameter::setMobilePhone);

        parameter.setPrivateAddress(createAddress(korrespondansepartResource.getAdresse(), new PrivateAddress__3()));

        synchronizePrivatePersonArgs.setParameter(parameter);
        return synchronizePrivatePersonArgs;
    }

    public SynchronizeEnterpriseArgs toEnterprise(KorrespondansepartResource korrespondansepartResource) {
        SynchronizeEnterpriseArgs synchronizeEnterpriseArgs = new SynchronizeEnterpriseArgs();
        Parameter__4 parameter = new Parameter__4();

        parameter.setName(korrespondansepartResource.getKorrespondansepartNavn());
        parameter.setEnterpriseNumber(korrespondansepartResource.getOrganisasjonsnummer().getIdentifikatorverdi());

        ofNullable(korrespondansepartResource.getKontaktinformasjon())
                .map(Kontaktinformasjon::getEpostadresse)
                .ifPresent(parameter::setEmail);

        ofNullable(korrespondansepartResource.getKontaktinformasjon())
                .map(Kontaktinformasjon::getMobiltelefonnummer)
                .ifPresent(parameter::setMobilePhone);

        ofNullable(korrespondansepartResource.getKontaktinformasjon())
                .map(Kontaktinformasjon::getTelefonnummer)
                .ifPresent(parameter::setPhoneNumber);

        ofNullable(korrespondansepartResource.getKontaktinformasjon())
                .map(Kontaktinformasjon::getNettsted)
                .ifPresent(parameter::setWeb);

        parameter.setPostAddress(createAddress(korrespondansepartResource.getAdresse(), new PostAddress__4()));

        synchronizeEnterpriseArgs.setParameter(parameter);
        return synchronizeEnterpriseArgs;
    }

    private PrivateAddress__3 createAddress(AdresseResource adresse, PrivateAddress__3 address ) {
        address.setCountry("NOR");
        ofNullable(adresse.getAdresselinje())
                .map(l -> l.get(0))
                .ifPresent(address::setStreetAddress);
        address.setZipCode(adresse.getPostnummer());
        address.setZipPlace(adresse.getPoststed());

        return address;
    }

    private PostAddress__4 createAddress(AdresseResource adresse, PostAddress__4 address) {
        address.setCountry("NOR");
        ofNullable(adresse.getAdresselinje())
                .map(l -> l.get(0))
                .ifPresent(address::setStreetAddress);
        address.setZipCode(adresse.getPostnummer());
        address.setZipPlace(adresse.getPoststed());

        return address;
    }
}
