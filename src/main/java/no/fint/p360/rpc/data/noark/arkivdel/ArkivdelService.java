package no.fint.p360.rpc.data.noark.arkivdel;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.ArkivdelResource;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class ArkivdelService {

    @Autowired
    private SupportService supportService;

    @Autowired
    private ArkivdelFactory arkivdelFactory;

    @Value("${fint.p360.tables.arkivdel:Noark Subarchive}")
    private String arkivdel;

    public Stream<ArkivdelResource> getArkivdel() {
        return supportService.getCodeTableRowResultStream(arkivdel)
                .map(arkivdelFactory::toFintResource);
    }
}
