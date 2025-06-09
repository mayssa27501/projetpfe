package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.projetkafka.command.entity.Locale;

public interface LocaleRepository extends JpaRepository<Locale, Long> {
}