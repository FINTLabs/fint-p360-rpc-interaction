package no.fint.p360.rpc.data.noark.codes.journalposttype;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.administrasjon.arkiv.JournalpostTypeResource;
import no.fint.p360.rpc.data.utilities.BegrepMapper;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Slf4j
@Service
public class JournalpostTypeService {

    @Autowired
    private SupportService supportService;

    @Value("${fint.p360.tables.document-category:code table: Document category}")
    private String documentCategoryTable;

    public Stream<JournalpostTypeResource> getDocumentCategoryTable() {
        return supportService.getCodeTableRowResultStream(documentCategoryTable)
                .map(BegrepMapper.mapValue(JournalpostTypeResource::new));
    }
}
