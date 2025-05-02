package tn.esprit.projetkafka.query.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.ModeleView;
import tn.esprit.projetkafka.query.entity.ProductView;
import tn.esprit.projetkafka.query.repository.ModeleViewRepository;
import tn.esprit.projetkafka.query.repository.ProductViewRepository;

import java.util.List;

@Service
public class ModeleQueryService {

    private final ModeleViewRepository repository;

    public ModeleQueryService(ModeleViewRepository repository) {
        this.repository = repository;
    }

    public List<ModeleView> getAllModeles() {
        return repository.findAll();
    }
}
