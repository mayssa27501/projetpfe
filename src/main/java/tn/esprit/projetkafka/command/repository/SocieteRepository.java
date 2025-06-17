package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.command.entity.Societe;

@Repository
public interface SocieteRepository extends JpaRepository<Societe, Long> {
}