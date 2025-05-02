package tn.esprit.projetkafka.query.entity;

import jakarta.persistence.*;
import tn.esprit.projetkafka.command.entity.Box;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Table(name = "modele_view")
public class ModeleView {
    @Id
    private Long id;
    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(name = "modele_view_attributes", joinColumns = @JoinColumn(name = "modele_view_id"))
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


}
