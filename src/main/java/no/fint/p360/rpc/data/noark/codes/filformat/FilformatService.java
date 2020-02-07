package no.fint.p360.rpc.data.noark.codes.filformat;

import no.fint.p360.rpc.data.utilities.BegrepMapper;
import no.fint.p360.rpc.p360Service.SupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class FilformatService {
    @Autowired
    private SupportService supportService;

    @Value("${fint.p360.tables.file-format:code table: File Format}")
    private String fileFormatTable;

    public Stream<FilformatResource> getFilformatTable() {
        return supportService
                .getCodeTableRowResultStream(fileFormatTable)
                .map(BegrepMapper.mapValue(FilformatResource::new));
    }
}
