package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projetkafka.command.entity.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {
}