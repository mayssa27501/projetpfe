package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Site;
import tn.esprit.projetkafka.command.service.SiteService;

@RestController
@RequestMapping("/sites")
@CrossOrigin(origins = "http://localhost:4200")
public class SiteController {
    private final SiteService service;

    @Autowired
    public SiteController(SiteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Site> createSite(@RequestBody Site site) {
        return ResponseEntity.ok(service.createSite(site));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Site> updateSite(@PathVariable Long id, @RequestBody Site site) {
        return ResponseEntity.ok(service.updateSite(id, site));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSite(@PathVariable Long id) {
        service.deleteSite(id);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }
}