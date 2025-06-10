package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.GroupeOnduleur;
import tn.esprit.projetkafka.command.service.GroupeOnduleurService;

import java.util.List;

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
    public ResponseEntity<GroupeOnduleur> createGroupeOnduleur(@RequestBody GroupeOnduleurRequest request) {
        return ResponseEntity.ok(service.createGroupeOnduleur(request.getGroupeOnduleur(), request.getOnduleurIds()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupeOnduleur> updateGroupeOnduleur(@PathVariable Long id, @RequestBody GroupeOnduleurRequest request) {
        return ResponseEntity.ok(service.updateGroupeOnduleur(id, request.getGroupeOnduleur(), request.getOnduleurIds()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroupeOnduleur(@PathVariable Long id) {
        service.deleteGroupeOnduleur(id);
        return ResponseEntity.noContent().build();
    }
}

// Nouvelle classe DTO pour encapsuler la requête
class GroupeOnduleurRequest {
    private GroupeOnduleur groupeOnduleur;
    private List<Long> onduleurIds;

    public GroupeOnduleur getGroupeOnduleur() { return groupeOnduleur; }
    public void setGroupeOnduleur(GroupeOnduleur groupeOnduleur) { this.groupeOnduleur = groupeOnduleur; }

    public List<Long> getOnduleurIds() { return onduleurIds; }
    public void setOnduleurIds(List<Long> onduleurIds) { this.onduleurIds = onduleurIds; }
}