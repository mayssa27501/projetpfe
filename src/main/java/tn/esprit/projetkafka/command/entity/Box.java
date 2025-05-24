package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String serialNumber;
    private String description;

    @ManyToOne
    private Modele modele;

    private Boolean isBlocked;

    @OneToMany(mappedBy = "box")
    private List<BoxCommand> boxCommands;
    @ElementCollection
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "attribute_value")
    @CollectionTable(name = "box_attributes", joinColumns = @JoinColumn(name = "box_id"))
    @JsonProperty("modeleAttributes")
    private Map<String, String> modeleAttributes = new HashMap<>();
    // Event type for Kafka events
    private String eventType;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Modele getModele() { return modele; }
    public void setModele(Modele modele) { this.modele = modele; }

    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }

    public List<BoxCommand> getBoxCommands() { return boxCommands; }
    public void setBoxCommands(List<BoxCommand> boxCommands) { this.boxCommands = boxCommands; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    public Map<String, String> getModeleAttributes() { return modeleAttributes; }
    public void setModeleAttributes(Map<String, String> modeleAttributes) { this.modeleAttributes = modeleAttributes; }
}