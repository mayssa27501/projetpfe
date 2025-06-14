package tn.esprit.projetkafka.query.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.query.entity.LocaleView;
@Repository
public interface LocaleViewRepository extends  MongoRepository<LocaleView, Long> {
}