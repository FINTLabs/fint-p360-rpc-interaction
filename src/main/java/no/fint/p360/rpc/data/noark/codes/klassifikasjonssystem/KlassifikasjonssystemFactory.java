package no.fint.p360.rpc.data.noark.codes.klassifikasjonssystem;


import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.KlassifikasjonssystemResource;
import no.fint.p360.rpc.data.utilities.FintUtils;
import no.p360.model.SupportService.CodeTableRow;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KlassifikasjonssystemFactory {

    public KlassifikasjonssystemResource toFintResource(CodeTableRow codeTableRow) {
        KlassifikasjonssystemResource klassifikasjonssystemResource = new KlassifikasjonssystemResource();

        klassifikasjonssystemResource.setSystemId(FintUtils.createIdentifikator(codeTableRow.getRecno().toString()));
        klassifikasjonssystemResource.setTittel(codeTableRow.getCode());
        klassifikasjonssystemResource.setBeskrivelse(codeTableRow.getDescription());

        return klassifikasjonssystemResource;

    }
}
