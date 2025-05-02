package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Lamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String power;       // Exemple : "50W"
    private String status;      // Exemple : "ON" ou "OFF"
    private String color;       // Optionnel : "Warm White", "Cool White", etc.

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}

