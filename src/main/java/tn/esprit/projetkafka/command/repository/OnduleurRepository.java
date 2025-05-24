package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.command.entity.Onduleur;

@Repository
public interface OnduleurRepository extends JpaRepository<Onduleur, Long> {
}