package no.fint.p360.rpc.data.utilities;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.felles.kompleksedatatyper.Kontaktinformasjon;
import no.fint.model.felles.kompleksedatatyper.Personnavn;
import no.fint.model.resource.felles.kompleksedatatyper.AdresseResource;
import no.p360.model.ContactService.*;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
public enum FintUtils {
    ;

    public static Identifikator createIdentifikator(String value) {
        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi(value);
        return identifikator;
    }

    public static boolean validIdentifikator(Identifikator input) {
        return Objects.nonNull(input) && StringUtils.isNotBlank(input.getIdentifikatorverdi());
    }

    public static Date parseDate(String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.ENGLISH);
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            log.warn("Unable to parse date {}", value);
            return null;
        }
    }
    public static Kontaktinformasjon createKontaktinformasjon(PrivatePerson result) {
        return getKontaktinformasjon(result.getEmail(), result.getMobilePhone(), result.getPhoneNumber());
    }

    public static Kontaktinformasjon createKontaktinformasjon(ContactPerson result) {
        return getKontaktinformasjon(result.getEmail(), result.getMobilePhone(), result.getPhoneNumber());
    }

    public static Kontaktinformasjon createKontaktinformasjon(Enterprise result) {
        return getKontaktinformasjon(result.getEmail(), result.getMobilePhone(), result.getPhoneNumber());
    }

    public static AdresseResource createAdresse(PrivatePerson result) {
        return optionalValue(result.getPostAddress()).map(FintUtils::createAdresseResource).orElse(null);
    }

    public static AdresseResource createAdresse(ContactPerson result) {
        return optionalValue(result.getPostAddress()).map(FintUtils::createAddressResource).orElse(null);
    }

    private static AdresseResource createAdresseResource(PostAddress__2 address) {
        PostAddress postAddress = new PostAddress();
        postAddress.setStreetAddress(address.getStreetAddress());
        postAddress.setZipPlace(address.getZipPlace());
        postAddress.setZipCode(address.getZipCode());
        return createAddressResource(postAddress);
    }
    private static AdresseResource createAdresseResource(PostAddress__1 address) {
        PostAddress postAddress = new PostAddress();
        postAddress.setStreetAddress(address.getStreetAddress());
        postAddress.setZipPlace(address.getZipPlace());
        postAddress.setZipCode(address.getZipCode());
        return createAddressResource(postAddress);
    }

    private static AdresseResource createAddressResource(PostAddress address){
        AdresseResource adresseResource = new AdresseResource();
        adresseResource.setAdresselinje(Collections.singletonList(address.getStreetAddress()));
        adresseResource.setPoststed(address.getZipPlace());
        adresseResource.setPostnummer(address.getZipCode());

        return adresseResource;
    }

    public static AdresseResource createAdresse(Enterprise result) {
        return optionalValue(result.getPostAddress()).map(FintUtils::createAdresseResource).orElse(null);
    }

    public static Personnavn parsePersonnavn(String input) {
        Personnavn personnavn = new Personnavn();
        if (StringUtils.contains(input, ", ")) {
            personnavn.setEtternavn(StringUtils.substringBefore(input, ", "));
            personnavn.setFornavn(StringUtils.substringAfter(input, ", "));
        } else if (StringUtils.contains(input, ' ')) {
            personnavn.setEtternavn(StringUtils.substringAfterLast(input, " "));
            personnavn.setFornavn(StringUtils.substringBeforeLast(input, " "));
        } else {
            throw new IllegalArgumentException("Ugyldig personnavn: " + input);
        }
        return personnavn;
    }

    public static String getFullNameString(PrivatePerson result) {
        return String.format("%s %s", result.getFirstName(), result.getLastName());
    }

    public static String getFullNameString(ContactPerson result) {
        return String.format("%s %s", result.getFirstName(), result.getLastName());
    }

    public static String getKontaktpersonString(Enterprise result) {

        if (!result.getContactRelations().isEmpty()) {
            return result.getContactRelations().get(0).getName();
        }
        return "";
    }

    public static <T> Optional<T> optionalValue(T element) {
        return Optional.ofNullable(element);
    }

    // FIXME: 2019-05-08 Must handle if all three elements is empty. Then we should return null
    private static Kontaktinformasjon getKontaktinformasjon(String email, String mobilePhone, String phoneNumber) {
        Kontaktinformasjon kontaktinformasjon = new Kontaktinformasjon();
        optionalValue(email).ifPresent(kontaktinformasjon::setEpostadresse);
        optionalValue(mobilePhone).ifPresent(kontaktinformasjon::setMobiltelefonnummer);
        optionalValue(phoneNumber).ifPresent(kontaktinformasjon::setTelefonnummer);
        return kontaktinformasjon;
    }
}
