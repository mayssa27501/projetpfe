package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table(name = "locale_view")
public class LocaleView {

    @Id
    private Long id;

    @JsonProperty("siteId")
    private Long siteId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("onduleurs")
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "locale_id")
    private List<OnduleurView> onduleurs = new ArrayList<>();

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSiteId() { return siteId; }
    public void setSiteId(Long siteId) { this.siteId = siteId; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<OnduleurView> getOnduleurs() { return onduleurs; }
    public void setOnduleurs(List<OnduleurView> onduleurs) { this.onduleurs = new ArrayList<>(onduleurs); }
}