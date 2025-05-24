package tn.esprit.projetkafka.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.OnduleurView;
import tn.esprit.projetkafka.query.service.OnduleurQueryService;

import java.util.List;

@RestController
@RequestMapping("/query/onduleurs")
@CrossOrigin(origins = "http://localhost:4200")
public class OnduleurQueryController {

    private final OnduleurQueryService service;

    @Autowired
    public OnduleurQueryController(OnduleurQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<OnduleurView>> getOnduleurs() {
        return ResponseEntity.ok(service.getAllOnduleurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OnduleurView> getOnduleurById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}