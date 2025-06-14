package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Table(name = "onduleur_view")
@Document
public class OnduleurView {

    @Id
    private Long id;

    @JsonProperty("siteId")
    private Long siteId;

    @JsonProperty("localeId")
    private Long localeId;

    @JsonProperty("boxId")
    private Long boxId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("dateCreation")
    private LocalDate dateCreation;

    @JsonProperty("heure")
    private String heure;

    @JsonProperty("consommationKwh")
    private Double consommationKwh;

    @JsonProperty("productionKwh")
    private Double productionKwh;

    @JsonProperty("bloque")
    private Boolean bloque;

    @JsonProperty("adresse")
    private String adresse;

    @JsonProperty("court")
    private String court;

    @JsonProperty("communication")
    private String communication;

    @JsonProperty("modeleOnduleur")
    private String modeleOnduleur;

    @JsonProperty("type")
    private String type;

    @JsonProperty("multiplicateur")
    private Integer multiplicateur;

    @JsonProperty("groupeOnduleurId")
    private Long groupeOnduleurId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }

    public Long getLocaleId() { return localeId; }
    public void setLocaleId(Long localeId) { this.localeId = localeId; }

    public Long getBoxId() { return boxId; }
    public void setBoxId(Long boxId) { this.boxId = boxId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

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

    public String getModeleOnduleur() { return modeleOnduleur; }
    public void setModeleOnduleur(String modeleOnduleur) { this.modeleOnduleur = modeleOnduleur; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Integer getMultiplicateur() { return multiplicateur; }
    public void setMultiplicateur(Integer multiplicateur) { this.multiplicateur = multiplicateur; }

    public Long getGroupeOnduleurId() { return groupeOnduleurId; }
    public void setGroupeOnduleurId(Long groupeOnduleurId) { this.groupeOnduleurId = groupeOnduleurId; }
}