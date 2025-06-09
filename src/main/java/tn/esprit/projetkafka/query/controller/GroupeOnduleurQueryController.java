package tn.esprit.projetkafka.query.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.GroupeOnduleurView;
import tn.esprit.projetkafka.query.service.GroupeOnduleurQueryService;

import java.util.List;

@RestController
@RequestMapping("/query/groupe-onduleurs")
@CrossOrigin(origins = "http://localhost:4200")
public class GroupeOnduleurQueryController {

    private final GroupeOnduleurQueryService service;

    @Autowired
    public GroupeOnduleurQueryController(GroupeOnduleurQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<GroupeOnduleurView>> getGroupeOnduleurs() {
        return ResponseEntity.ok(service.getAllGroupeOnduleurs());
    }
}