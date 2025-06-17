package tn.esprit.projetkafka.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.query.entity.SocieteView;

@Repository
public interface SocieteViewRepository extends MongoRepository<SocieteView, Long> {
}