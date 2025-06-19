package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;
    private String address;
    private String timeZone;
    private String positionGps;
    private Boolean isBlocked;
    private Boolean isPrincipal;

    @OneToMany(mappedBy = "site", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnore
    private List<Locale> locales = new ArrayList<>(); // Added relationship to Locale

    @OneToMany(mappedBy = "site")
    @JsonIgnore
    private List<GroupeOnduleur> groupeOnduleurs;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPositionGps() {
        return positionGps;
    }

    public void setPositionGps(String positionGps) {
        this.positionGps = positionGps;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Boolean getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    public List<GroupeOnduleur> getGroupeOnduleurs() {
        return groupeOnduleurs;
    }

    public void setGroupeOnduleurs(List<GroupeOnduleur> groupeOnduleurs) {
        this.groupeOnduleurs = groupeOnduleurs;
    }
}