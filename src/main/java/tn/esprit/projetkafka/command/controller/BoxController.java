package tn.esprit.projetkafka.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.command.service.BoxService;

@RestController
@RequestMapping("/boxes")
@CrossOrigin(origins = "http://localhost:4200")
public class BoxController {
    private final BoxService service;

    @Autowired
    public BoxController(BoxService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Box> createBox(@RequestBody Box box) {
        return ResponseEntity.ok(service.createBox(box));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Box> updateBox(@PathVariable Long id, @RequestBody Box box) {
        return ResponseEntity.ok(service.updateBox(id, box));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBox(@PathVariable Long id) {
        service.deleteBox(id);
        return ResponseEntity.noContent().build();
    }
}