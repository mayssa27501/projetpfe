package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Modele;
import tn.esprit.projetkafka.command.service.ModeleService;

@RestController
@RequestMapping("/modeles")
public class ModeleController {
    private final ModeleService service;

    @Autowired
    public ModeleController(ModeleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Modele> createModele(@RequestBody Modele modele) {
        return ResponseEntity.ok(service.createModele(modele));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modele> updateModele(@PathVariable Long id, @RequestBody Modele modele) {
        return ResponseEntity.ok(service.updateModele(id, modele));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModele(@PathVariable Long id) {
        service.deleteModele(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}
