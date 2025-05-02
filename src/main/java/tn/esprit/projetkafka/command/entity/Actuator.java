package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Actuator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String status; // ex: ON, OFF

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}

