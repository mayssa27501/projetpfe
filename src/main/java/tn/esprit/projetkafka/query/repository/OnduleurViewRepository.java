package tn.esprit.projetkafka.query.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.query.entity.OnduleurView;

import java.util.List;

@Repository
public interface OnduleurViewRepository extends MongoRepository<OnduleurView, Long> {
    List<OnduleurView> findByBoxId(Long boxId);
    void deleteByGroupeOnduleurId(Long groupeOnduleurId);
}