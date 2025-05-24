package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Onduleur> onduleurs = new ArrayList<>();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<Onduleur> getOnduleurs() { return onduleurs; }
    public void setOnduleurs(List<Onduleur> onduleurs) { this.onduleurs = onduleurs; }

    // Convenience method to add Onduleur
    public void addOnduleur(Onduleur onduleur) {
        onduleurs.add(onduleur);
        onduleur.setSite(this);
    }

    // Convenience method to remove Onduleur
    public void removeOnduleur(Onduleur onduleur) {
        onduleurs.remove(onduleur);
        onduleur.setSite(null);
    }
}