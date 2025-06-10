package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "onduleur")
public class Onduleur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String code;

    private String description;

    @ManyToOne
    private Site site;

//    @ManyToOne
//    @JoinColumn(name = "locale_id")
//    private Locale locale;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne
    @JoinColumn(name = "groupe_onduleur_id")
    @JsonBackReference
    private GroupeOnduleur groupeOnduleur;

    @Column(name = "onduleur_index")
    private Integer index;

    private LocalDate dateCommunication;

    private String heure;

    private Double consommationKwh;

    private Double productionKwh;

    private Boolean bloque;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }
//
//    public Locale getLocale() { return locale; }
//    public void setLocale(Locale locale) { this.locale = locale; }

    public Box getBox() { return box; }
    public void setBox(Box box) { this.box = box; }

    public GroupeOnduleur getGroupeOnduleur() { return groupeOnduleur; }
    public void setGroupeOnduleur(GroupeOnduleur groupeOnduleur) { this.groupeOnduleur = groupeOnduleur; }

    public Integer getIndex() { return index; }
    public void setIndex(Integer index) { this.index = index; }

    public LocalDate getDateCommunication() { return dateCommunication; }
    public void setDateCommunication(LocalDate dateCommunication) { this.dateCommunication = dateCommunication; }

    public String getHeure() { return heure; }
    public void setHeure(String heure) { this.heure = heure; }

    public Double getConsommationKwh() { return consommationKwh; }
    public void setConsommationKwh(Double consommationKwh) { this.consommationKwh = consommationKwh; }

    public Double getProductionKwh() { return productionKwh; }
    public void setProductionKwh(Double productionKwh) { this.productionKwh = productionKwh; }

    public Boolean getBloque() { return bloque; }
    public void setBloque(Boolean bloque) { this.bloque = bloque; }
}