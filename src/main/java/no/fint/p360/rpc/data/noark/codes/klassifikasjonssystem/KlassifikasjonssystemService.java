package no.fint.p360.rpc.data.noark.codes.klassifikasjonssystem;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.KlassifikasjonssystemResource;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class KlassifikasjonssystemService {

    @Autowired
    private SupportService supportService;

    @Autowired
    private KlassifikasjonssystemFactory factory;

    @Value("${fint.p360.tables.classification-type:Noark Classification type}")
    private String codeTableName;

    public Stream<KlassifikasjonssystemResource> getKlassifikasjonssystem() {
        return supportService.getCodeTableRowResultStream(codeTableName)
                .map(factory::toFintResource);
    }
}
