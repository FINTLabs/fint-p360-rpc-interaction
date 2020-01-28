package no.fint.p360.rpc;

import no.p360.model.CaseService.Case;
import no.p360.model.CaseService.GetCasesArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class P360Controller {

    @Autowired
    private P360Service p360Service;

    @GetMapping("sak/systemid/{systemid}")
    public ResponseEntity<Case> getSak(@PathVariable int systemid) {

        return ResponseEntity.ok().body(p360Service.getCaseBySystemId(systemid));
    }

    @GetMapping("getcases")
    public ResponseEntity<List<Case>> getCases(@RequestBody GetCasesArgs getCasesArgs) {

        return ResponseEntity.ok().body(p360Service.getCases(getCasesArgs));
    }
}
