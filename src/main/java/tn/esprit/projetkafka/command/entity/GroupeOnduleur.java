package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groupe_onduleur")
public class GroupeOnduleur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String description;

    private String operation;

    @ManyToOne
    private Site site;

    private Boolean bloque;

    @OneToMany(mappedBy = "groupeOnduleur")
    private List<Onduleur> onduleurs = new ArrayList<>();

    // Event type for Kafka events
    private String eventType;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }

    public Boolean getBloque() { return bloque; }
    public void setBloque(Boolean bloque) { this.bloque = bloque; }

    public List<Onduleur> getOnduleurs() { return onduleurs; }
    public void setOnduleurs(List<Onduleur> onduleurs) { this.onduleurs = onduleurs; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}