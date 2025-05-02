package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;

@Entity
public class BoxCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commandType;
    private String status;

    @ManyToOne
    @JoinColumn(name = "box_id")
    private Box box;

    // Getters and Setters
    // ...
}

