package no.fint.p360.rpc;

import no.fint.p360.rpc.p360Service.CaseService;
import no.p360.model.CaseService.Case;
import no.p360.model.CaseService.CreateCaseArgs;
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
    private CaseService caseService;

    @GetMapping("sak/systemid/{systemid}")
    public ResponseEntity<Case> getSak(@PathVariable int systemid) {

        return ResponseEntity.ok().body(caseService.getCaseBySystemId(systemid));
    }

    @GetMapping("getcase")
    public ResponseEntity<Case> getCase(@RequestBody GetCasesArgs getCasesArgs) throws Exception {

        return ResponseEntity.ok().body(caseService.getCase(getCasesArgs));
    }

    @GetMapping("getcases")
    public ResponseEntity<List<Case>> getCases(@RequestBody GetCasesArgs getCasesArgs) {

        return ResponseEntity.ok().body(caseService.getCases(getCasesArgs));
    }
}
