package tn.esprit.projetkafka.query.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.query.entity.ModeleView;
import tn.esprit.projetkafka.query.service.ModeleQueryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/query/modeles")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ModeleQueryController {
    private final ModeleQueryService service;

    @GetMapping
    public ResponseEntity<List<ModeleView>> getModeles() {
        return ResponseEntity.ok(service.getAllModeles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModeleView> getModeleById(@PathVariable Long id) {
        Optional<ModeleView> modeleView = service.getModeleById(id);
        return modeleView.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}