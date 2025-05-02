package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.command.entity.Box;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {
}
