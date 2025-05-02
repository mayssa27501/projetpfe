package tn.esprit.projetkafka.query.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.BoxView;
import tn.esprit.projetkafka.query.repository.BoxViewRepository;

import java.util.List;

@Service
public class BoxQueryService {

    private final BoxViewRepository repository;

    public BoxQueryService(BoxViewRepository repository) {
        this.repository = repository;
    }

    public List<BoxView> getAllBoxes() {
        return repository.findAll();
    }
}
