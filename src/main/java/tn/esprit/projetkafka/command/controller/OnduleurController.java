package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.command.service.OnduleurService;

@RestController
@RequestMapping("/onduleurs")
@CrossOrigin(origins = "http://localhost:4200")
public class OnduleurController {
    private final OnduleurService service;

    @Autowired
    public OnduleurController(OnduleurService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Onduleur> createOnduleur(@RequestBody Onduleur onduleur) {
        return ResponseEntity.ok(service.createOnduleur(onduleur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Onduleur> updateOnduleur(@PathVariable Long id, @RequestBody Onduleur onduleur) {
        return ResponseEntity.ok(service.updateOnduleur(id, onduleur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOnduleur(@PathVariable Long id) {
        service.deleteOnduleur(id);
        return ResponseEntity.noContent().build();
    }
}