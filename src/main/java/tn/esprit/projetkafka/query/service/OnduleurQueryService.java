package tn.esprit.projetkafka.query.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.OnduleurView;
import tn.esprit.projetkafka.query.repository.OnduleurViewRepository;

import java.util.List;

@Service
public class OnduleurQueryService {

    private final OnduleurViewRepository repository;

    public OnduleurQueryService(OnduleurViewRepository repository) {
        this.repository = repository;
    }

    public List<OnduleurView> getAllOnduleurs() {
        return repository.findAll();
    }

    public OnduleurView findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("OnduleurView not found with id: " + id));
    }
}