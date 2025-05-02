package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String reference;
    private String mesure;

    @ManyToOne
    @JoinColumn(name = "box_id") // Foreign key vers Box
    private Box box;
}
