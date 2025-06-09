package tn.esprit.projetkafka.query.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.query.entity.GroupeOnduleurView;
import tn.esprit.projetkafka.query.repository.GroupeOnduleurViewRepository;

import java.util.List;

@Service
public class GroupeOnduleurQueryService {

    private final GroupeOnduleurViewRepository repository;

    public GroupeOnduleurQueryService(GroupeOnduleurViewRepository repository) {
        this.repository = repository;
    }

    public List<GroupeOnduleurView> getAllGroupeOnduleurs() {
        return repository.findAll();
    }
}