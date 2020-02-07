package no.fint.p360.rpc.data.noark.codes.tilgangsrestriksjon;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.TilgangsrestriksjonResource;
import no.fint.p360.rpc.data.utilities.BegrepMapper;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class TilgangsrestriksjonService {

    @Autowired
    private SupportService supportService;
    @Value("${fint.p360.tables.access-code:code table: Access code}")
    private String tableName;

    public Stream<TilgangsrestriksjonResource> getAccessCodeTable() {
        return supportService.getCodeTableRowResultStream(tableName)
                .map(BegrepMapper.mapValue(TilgangsrestriksjonResource::new));
    }

}
