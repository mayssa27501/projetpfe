package tn.esprit.projetkafka.query.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.projetkafka.query.entity.ProductView;
import tn.esprit.projetkafka.query.service.ProductQueryService;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/query/products")
public class ProductQueryController {
    private final ProductQueryService service;

    @Autowired
    public ProductQueryController(ProductQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProductView>> getProducts() {
        return ResponseEntity.ok(service.getAllProducts());
    }
}