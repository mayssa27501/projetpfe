package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Table(name = "onduleur_view")
public class OnduleurView {

    @Id
    private Long id;

    @JsonProperty("siteId")
    private Long siteId;

    @JsonProperty("localeId")
    private Long localeId;

    @JsonProperty("boxId")
    private Long boxId;

    @JsonProperty("boxCode")
    private String boxCode;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("index")
    private Integer index;

    @JsonProperty("dateCommunication")
    private LocalDate dateCommunication;

    @JsonProperty("heure")
    private String heure;

    @JsonProperty("consommationKwh")
    private Double consommationKwh;

    @JsonProperty("productionKwh")
    private Double productionKwh;

    @JsonProperty("bloque")
    private Boolean bloque;
    @JsonProperty("groupeOnduleurId")
    private Long groupeOnduleurId;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }

    public Long getLocaleId() { return localeId; }
    public void setLocaleId(Long localeId) { this.localeId = localeId; }

    public Long getBoxId() { return boxId; }
    public void setBoxId(Long boxId) { this.boxId = boxId; }

    public String getBoxCode() { return boxCode; }
    public void setBoxCode(String boxCode) { this.boxCode = boxCode; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

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
    public Long getGroupeOnduleurId() { return groupeOnduleurId; }
    public void setGroupeOnduleurId(Long groupeOnduleurId) { this.groupeOnduleurId = groupeOnduleurId; }
}