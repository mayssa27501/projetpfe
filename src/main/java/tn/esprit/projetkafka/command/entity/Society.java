package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "society")
public class Society {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    private String description;

//    @OneToMany(mappedBy = "society")
//    @JsonManagedReference
//    private List<Site> sites = new ArrayList<>();
//
//    // Utility methods for managing the relationship with Site
//    public void addSite(Site site) {
//        sites.add(site);
//        site.setSociety(this);
//    }
//
//    public void removeSite(Site site) {
//        sites.remove(site);
//        site.setSociety(null);
//    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

//    public List<Site> getSites() { return sites; }
//    public void setSites(List<Site> sites) {
//        this.sites.clear();
//        sites.forEach(this::addSite);
//    }
}