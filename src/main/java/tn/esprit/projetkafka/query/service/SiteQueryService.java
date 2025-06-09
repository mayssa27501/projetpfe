package tn.esprit.projetkafka.query.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.SiteView;
import tn.esprit.projetkafka.query.repository.SiteViewRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SiteQueryService {
    private final SiteViewRepository repository;

    public List<SiteView> getAllSites() {
        return repository.findAll();
    }

    public Optional<SiteView> getSiteById(Long id) {
        return repository.findById(id);
    }
}