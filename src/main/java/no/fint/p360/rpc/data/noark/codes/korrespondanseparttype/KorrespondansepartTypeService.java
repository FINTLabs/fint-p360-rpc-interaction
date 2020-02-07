package no.fint.p360.rpc.data.noark.codes.korrespondanseparttype;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.KorrespondansepartTypeResource;
import no.fint.p360.rpc.data.utilities.BegrepMapper;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class KorrespondansepartTypeService {

    @Autowired
    private SupportService supportService;

    @Value("${fint.p360.tables.contact-role:code table: Activity - Contact role}")
    private String contactRoleTable;

    public Stream<KorrespondansepartTypeResource> getKorrespondansepartType() {
        return supportService.getCodeTableRowResultStream(contactRoleTable)
                .map(BegrepMapper.mapValue(KorrespondansepartTypeResource::new));
    }
}
