package no.fint.p360.rpc.data.noark.codes.journalstatus;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.JournalStatusResource;
import no.fint.p360.rpc.data.utilities.BegrepMapper;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class JournalStatusService {

    @Autowired
    private SupportService supportService;

    @Value("${fint.p360.tables.journal-status:code table: Journal status}")
    private String journalStatusTable;

    public Stream<JournalStatusResource> getJournalStatusTable() {
        return supportService.getCodeTableRowResultStream(journalStatusTable)
                .map(BegrepMapper.mapValue(JournalStatusResource::new));
    }
}
