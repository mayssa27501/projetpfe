package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Societe;
import tn.esprit.projetkafka.command.service.SocieteService;

@RestController
@RequestMapping("/societes")
@CrossOrigin(origins = "http://localhost:4200")
public class SocieteController {
    private final SocieteService service;

    @Autowired
    public SocieteController(SocieteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Societe> createSociete(@RequestBody Societe societe) {
        return ResponseEntity.ok(service.createSociete(societe));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Societe> updateSociete(@PathVariable Long id, @RequestBody Societe societe) {
        return ResponseEntity.ok(service.updateSociete(id, societe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSociete(@PathVariable Long id) {
        service.deleteSociete(id);
        return ResponseEntity.noContent().build();
    }
}