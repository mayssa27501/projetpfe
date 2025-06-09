package tn.esprit.projetkafka.command.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.command.service.OnduleurService;
import tn.esprit.projetkafka.event.producer.OnduleurEventProducer;

@RestController
@RequestMapping("/onduleurs")
@CrossOrigin(origins = "http://localhost:4200")
public class OnduleurController {
    private static final Logger logger = LoggerFactory.getLogger(OnduleurController.class);
    private final OnduleurService service;
    private final OnduleurEventProducer eventProducer;

    @Autowired
    public OnduleurController(OnduleurService service, OnduleurEventProducer eventProducer) {
        this.service = service;
        this.eventProducer = eventProducer;
    }

    @PostMapping
    public ResponseEntity<?> createOnduleur(@RequestBody Onduleur onduleur) {
        logger.info("Received create Onduleur request: code={}, box={}",
                onduleur.getCode(), onduleur.getBox() != null ? "id=" + onduleur.getBox().getId() : "null");

        try {
            Onduleur createdOnduleur = service.createOnduleur(onduleur);

            // Envoi de l'événement Kafka après création réussie
            eventProducer.sendOnduleurEvent(createdOnduleur);

            return ResponseEntity.ok(createdOnduleur);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid request: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            logger.error("Failed to create Onduleur: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOnduleur(@PathVariable Long id, @RequestBody Onduleur onduleur) {
        logger.info("Received update Onduleur request: id={}, code={}, box={}",
                id, onduleur.getCode(), onduleur.getBox() != null ? "id=" + onduleur.getBox().getId() : "null");

        try {
            Onduleur updatedOnduleur = service.updateOnduleur(id, onduleur);

            // Envoi de l'événement Kafka après mise à jour réussie
            eventProducer.sendOnduleurEvent(updatedOnduleur);

            return ResponseEntity.ok(updatedOnduleur);
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid request: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            logger.error("Failed to update Onduleur: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOnduleur(@PathVariable Long id) {
        logger.info("Received delete Onduleur request: id={}", id);

        try {
            service.deleteOnduleur(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            logger.error("Failed to delete Onduleur: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
