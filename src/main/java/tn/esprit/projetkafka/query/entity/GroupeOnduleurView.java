package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Table(name = "groupe_onduleur_view")
public class GroupeOnduleurView {

    @Id
    private Long id;

    @JsonProperty("siteId")
    private Long siteId;

    @JsonProperty("siteName")
    private String siteName;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("operation")
    private String operation;

    @JsonProperty("bloque")
    private Boolean bloque;

    @JsonProperty("onduleurs")
    @DBRef(lazy = true)
    private List<OnduleurView> onduleurs = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }

    public String getSiteName() { return siteName; }
    public void setSiteName(String siteName) { this.siteName = siteName; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getOperation() { return operation; }
    public void setOperation(String operation) { this.operation = operation; }

    public Boolean getBloque() { return bloque; }
    public void setBloque(Boolean bloque) { this.bloque = bloque; }

    public List<OnduleurView> getOnduleurs() { return onduleurs; }
    public void setOnduleurs(List<OnduleurView> onduleurs) { this.onduleurs = new ArrayList<>(onduleurs); }
}