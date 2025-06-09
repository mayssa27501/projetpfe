package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.GroupeOnduleur;
import tn.esprit.projetkafka.command.service.GroupeOnduleurService;

@RestController
@RequestMapping("/groupe-onduleurs")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupeOnduleurController {
    private final GroupeOnduleurService service;

    @Autowired
    public GroupeOnduleurController(GroupeOnduleurService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GroupeOnduleur> createGroupeOnduleur(@RequestBody GroupeOnduleur groupeOnduleur) {
        return ResponseEntity.ok(service.createGroupeOnduleur(groupeOnduleur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupeOnduleur> updateGroupeOnduleur(@PathVariable Long id, @RequestBody GroupeOnduleur groupeOnduleur) {
        return ResponseEntity.ok(service.updateGroupeOnduleur(id, groupeOnduleur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupeOnduleur(@PathVariable Long id) {
        service.deleteGroupeOnduleur(id);
        return ResponseEntity.noContent().build();
    }
}