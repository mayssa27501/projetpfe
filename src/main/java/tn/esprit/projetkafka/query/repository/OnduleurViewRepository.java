package tn.esprit.projetkafka.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projetkafka.query.entity.OnduleurView;

import java.util.List;

public interface OnduleurViewRepository extends MongoRepository<OnduleurView, Long> {
    List<OnduleurView> findByBoxId(Long boxId);
}