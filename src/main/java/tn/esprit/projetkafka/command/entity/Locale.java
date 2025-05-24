package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "locale")
public class Locale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "locale")
    private List<Onduleur> onduleurs;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<Onduleur> getOnduleurs() { return onduleurs; }
    public void setOnduleurs(List<Onduleur> onduleurs) { this.onduleurs = onduleurs; }
}