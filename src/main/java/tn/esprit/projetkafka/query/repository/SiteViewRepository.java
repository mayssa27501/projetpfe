package tn.esprit.projetkafka.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projetkafka.query.entity.SiteView;

public interface SiteViewRepository extends JpaRepository<SiteView, Long> {
}