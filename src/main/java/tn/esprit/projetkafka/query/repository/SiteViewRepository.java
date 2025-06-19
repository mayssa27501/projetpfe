package tn.esprit.projetkafka.query.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import tn.esprit.projetkafka.query.entity.SiteView;

public interface SiteViewRepository extends MongoRepository<SiteView, Long> {
}