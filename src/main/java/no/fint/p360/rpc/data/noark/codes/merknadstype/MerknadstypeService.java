package no.fint.p360.rpc.data.noark.codes.merknadstype;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.MerknadstypeResource;
import no.fint.p360.rpc.data.utilities.BegrepMapper;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class MerknadstypeService {

    @Autowired
    private SupportService supportService;

    @Value("${fint.p360.tables.note-type:code table: Note type}")
    private String codeTableName;

    public Stream<MerknadstypeResource> getMerknadstype() {
        return supportService.getCodeTableRowResultStream(codeTableName)
                .map(BegrepMapper.mapValue(MerknadstypeResource::new));
    }
}
