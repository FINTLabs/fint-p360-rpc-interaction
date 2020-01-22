package no.fint.p360.rpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class P360Controller {

    @Autowired
    private P360Service p360Service;

    @GetMapping("sak/systemid/{systemid}")
    public ResponseEntity<P360Case> getSak(@PathVariable int systemid) {

        return ResponseEntity.ok().body(p360Service.getCase(systemid));
    }
}
