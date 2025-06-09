package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;


@Table(name = "sensor_group_view")
public class SensorGroupView {

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
}