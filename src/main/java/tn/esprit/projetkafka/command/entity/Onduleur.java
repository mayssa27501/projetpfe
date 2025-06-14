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

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "box_id")
    private Box box;

    @ManyToOne
    @JoinColumn(name = "groupe_onduleur_id")
    @JsonBackReference
    private GroupeOnduleur groupeOnduleur;

    @Column(name = "onduleur_index")
    private Integer index;

    private LocalDate dateCreation;

    private String heure;

    private Double consommationKwh;

    private Double productionKwh;

    private Boolean bloque;

    @Column(name = "adresse_ip")
    private String adresse;

    @Column(name = "court")
    private String court;

    @Column(name = "communication")
    private String communication;

    @Enumerated(EnumType.STRING)
    @Column(name = "modele_onduleur")
    private ModeleOnduleur modeleOnduleur;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeOnduleur type;

    @Column(name = "multiplicateur")
    private Integer multiplicateur;

    public enum TypeOnduleur {
        ONDULEUR_SOLAIRE
        // Add other type values as needed
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Site getSite() { return site; }
    public void setSite(Site site) { this.site = site; }

    public Box getBox() { return box; }
    public void setBox(Box box) { this.box = box; }

    public GroupeOnduleur getGroupeOnduleur() { return groupeOnduleur; }
    public void setGroupeOnduleur(GroupeOnduleur groupeOnduleur) { this.groupeOnduleur = groupeOnduleur; }

    public Integer getIndex() { return index; }
    public void setIndex(Integer index) { this.index = index; }

    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }

    public String getHeure() { return heure; }
    public void setHeure(String heure) { this.heure = heure; }

    public Double getConsommationKwh() { return consommationKwh; }
    public void setConsommationKwh(Double consommationKwh) { this.consommationKwh = consommationKwh; }

    public Double getProductionKwh() { return productionKwh; }
    public void setProductionKwh(Double productionKwh) { this.productionKwh = productionKwh; }

    public Boolean getBloque() { return bloque; }
    public void setBloque(Boolean bloque) { this.bloque = bloque; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getCourt() { return court; }
    public void setCourt(String court) { this.court = court; }

    public String getCommunication() { return communication; }
    public void setCommunication(String communication) { this.communication = communication; }

    public ModeleOnduleur getModeleOnduleur() { return modeleOnduleur; }
    public void setModeleOnduleur(ModeleOnduleur modeleOnduleur) { this.modeleOnduleur = modeleOnduleur; }

    public TypeOnduleur getType() { return type; }
    public void setType(TypeOnduleur type) { this.type = type; }

    public Integer getMultiplicateur() { return multiplicateur; }
    public void setMultiplicateur(Integer multiplicateur) { this.multiplicateur = multiplicateur; }
}