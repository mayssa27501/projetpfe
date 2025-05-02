package tn.esprit.projetkafka.query.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.ModeleView;
import tn.esprit.projetkafka.query.service.ModeleQueryService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/query/modeles")
public class ModeleQueryController {
    private final ModeleQueryService service;

    @Autowired
    public ModeleQueryController(ModeleQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ModeleView>> getModeles() {
        return ResponseEntity.ok(service.getAllModeles());
    }
}