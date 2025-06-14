package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Locale;
import tn.esprit.projetkafka.command.service.LocaleService;

@RestController
@RequestMapping("/locales")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class LocaleController {
    private final LocaleService service;

    @Autowired
    public LocaleController(LocaleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Locale> createLocale(@RequestBody Locale locale) {
        return ResponseEntity.ok(service.createLocale(locale));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locale> updateLocale(@PathVariable Long id, @RequestBody Locale locale) {
        return ResponseEntity.ok(service.updateLocale(id, locale));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocale(@PathVariable Long id) {
        service.deleteLocale(id);
        return ResponseEntity.noContent().build();
    }
}