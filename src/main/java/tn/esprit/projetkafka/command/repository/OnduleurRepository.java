package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projetkafka.command.entity.Onduleur;

public interface OnduleurRepository extends JpaRepository<Onduleur, Long> {
    boolean existsByCode(String code);
}