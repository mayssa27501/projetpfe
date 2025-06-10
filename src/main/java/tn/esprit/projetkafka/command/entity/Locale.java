package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "locale")
public class Locale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private String description;

    // @ManyToOne
    // @JoinColumn(name = "site_id", nullable = false)
    // @JsonBackReference
    // private Site site;

    // Commenter la relation avec Onduleur
    // @OneToMany(mappedBy = "locale", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    // private List<Onduleur> onduleurs = new ArrayList<>();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // @Override
    // public Site getSite() { return site; }
    // public void setSite(Site site) { this.site = site; }

    // Commenter les getters/setters pour Onduleur
    // public List<Onduleur> getOnduleurs() { return onduleurs; }
    // public void setOnduleurs(List<Onduleur> onduleurs) { this.onduleurs = onduleurs; }

    // Equals and HashCode for entity comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Locale that = (Locale) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }
}