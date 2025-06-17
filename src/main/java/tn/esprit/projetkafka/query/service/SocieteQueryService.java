package tn.esprit.projetkafka.query.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.SocieteView;
import tn.esprit.projetkafka.query.repository.SocieteViewRepository;

import java.util.List;

@Service
public class SocieteQueryService {

    private final SocieteViewRepository repository;

    public SocieteQueryService(SocieteViewRepository repository) {
        this.repository = repository;
    }

    public List<SocieteView> getAllSocietes() {
        return repository.findAll();
    }

    public SocieteView getSocieteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Societe not found with ID: " + id));
    }
}