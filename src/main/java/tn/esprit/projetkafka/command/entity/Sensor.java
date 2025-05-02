package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double value;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;
}
