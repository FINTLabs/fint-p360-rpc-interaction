package no.fint.p360.rpc.data.noark.codes.klasse;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.KlasseResource;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class KlasseService {

    @Autowired
    private SupportService supportService;

    @Autowired
    private KlasseFactory factory;

    @Value("${fint.p360.tables.classification-code:Noark Classification code}")
    private String codeTableName;

    public Stream<KlasseResource> getKlasse() {
        return supportService.getCodeTableRowResultStream(codeTableName)
                .map(factory::toFintResource);
    }
}
