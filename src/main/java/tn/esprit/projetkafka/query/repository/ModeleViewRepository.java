package tn.esprit.projetkafka.query.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.query.entity.ModeleView;

@Repository
public interface ModeleViewRepository extends MongoRepository<ModeleView, Long> {
}
