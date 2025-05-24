package tn.esprit.projetkafka.query.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.ModeleView;
import tn.esprit.projetkafka.query.repository.ModeleViewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ModeleQueryService {
    private final ModeleViewRepository repository;

    public List<ModeleView> getAllModeles() {
        return repository.findAll();
    }

    public Optional<ModeleView> getModeleById(Long id) {
        return repository.findById(id);
    }
}