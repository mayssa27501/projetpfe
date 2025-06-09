package tn.esprit.projetkafka.query.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.query.entity.SiteView;
import tn.esprit.projetkafka.query.service.SiteQueryService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/query/sites")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class SiteQueryController {
    private final SiteQueryService service;

    @GetMapping
    public ResponseEntity<List<SiteView>> getSites() {
        return ResponseEntity.ok(service.getAllSites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteView> getSiteById(@PathVariable Long id) {
        Optional<SiteView> siteView = service.getSiteById(id);
        return siteView.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}