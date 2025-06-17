package tn.esprit.projetkafka.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.SocieteView;
import tn.esprit.projetkafka.query.service.SocieteQueryService;

import java.util.List;

@RestController
@RequestMapping("/query/societes")
@CrossOrigin(origins = "http://localhost:4200")
public class SocieteQueryController {

    private final SocieteQueryService service;

    @Autowired
    public SocieteQueryController(SocieteQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SocieteView>> getSocietes() {
        return ResponseEntity.ok(service.getAllSocietes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocieteView> getSocieteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSocieteById(id));
    }
}