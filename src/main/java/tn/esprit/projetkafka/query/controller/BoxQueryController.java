package tn.esprit.projetkafka.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.BoxView;
import tn.esprit.projetkafka.query.service.BoxQueryService;

import java.util.List;

@RestController
@RequestMapping("/query/boxes")
@CrossOrigin(origins = "http://localhost:4200")
public class BoxQueryController {

    private final BoxQueryService service;

    @Autowired
    public BoxQueryController(BoxQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<BoxView>> getBoxes() {
        return ResponseEntity.ok(service.getAllBoxes());
    }
}
