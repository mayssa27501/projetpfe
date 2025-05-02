package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.command.entity.Modele;

@Repository
public interface ModeleRepository extends JpaRepository<Modele, Long> {
}