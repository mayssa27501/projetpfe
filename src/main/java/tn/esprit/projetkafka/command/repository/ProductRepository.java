package tn.esprit.projetkafka.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.projetkafka.command.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

