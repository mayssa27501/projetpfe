package tn.esprit.projetkafka.query.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.LocaleView;
import tn.esprit.projetkafka.query.repository.LocaleViewRepository;

import java.util.List;

@Service
public class LocaleQueryService {
    private final LocaleViewRepository repository;

    public LocaleQueryService(LocaleViewRepository repository) {
        this.repository = repository;
    }

    public List<LocaleView> getAllLocales() {
        return repository.findAll();
    }
}