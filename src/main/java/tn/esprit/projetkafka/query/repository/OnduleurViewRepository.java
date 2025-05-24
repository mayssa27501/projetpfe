package tn.esprit.projetkafka.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.query.entity.OnduleurView;

@Repository
public interface OnduleurViewRepository extends MongoRepository<OnduleurView, Long> {
}