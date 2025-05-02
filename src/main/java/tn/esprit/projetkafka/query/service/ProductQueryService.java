package tn.esprit.projetkafka.query.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.ProductView;
import tn.esprit.projetkafka.query.repository.ProductViewRepository;

import java.util.List;

@Service
public class ProductQueryService {

    private final ProductViewRepository repository;

    public ProductQueryService(ProductViewRepository repository) {
        this.repository = repository;
    }

    public List<ProductView> getAllProducts() {
        return repository.findAll();
    }
}
