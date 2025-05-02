package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
@Entity
@Table(name = "modele")
public class Modele {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ElementCollection
    @CollectionTable(
            name = "modele_attributes",
            joinColumns = @JoinColumn(name = "modele_id")
    )
//    @MapKeyColumn(name = "attribute_key")
//    @Column(name = "attribute_value")
    private Map<String, String> attributes = new HashMap<>();


    @OneToMany(mappedBy = "modele")
    private List<Box> boxes;
    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }


}
