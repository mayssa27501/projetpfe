package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Table(name = "box_view")
public class BoxView {

    @Id
    private Long id;

    @JsonProperty("modeleId")
    private Long modeleId;

    @JsonProperty("modeleName")
    private String modeleName;

    @JsonProperty("code")
    private String code;

    @JsonProperty("serialNumber")
    private String serialNumber;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isBlocked")
    private Boolean isBlocked;

    @JsonProperty("modeleAttributes")
    private Map<String, String> modeleAttributes = new HashMap<>();

    @JsonProperty("onduleurs")
    private List<Long> onduleurIds = new ArrayList<>(); // Store only IDs to avoid duplication

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getModeleId() { return modeleId; }
    public void setModeleId(Long modeleId) { this.modeleId = modeleId; }

    public String getModeleName() { return modeleName; }
    public void setModeleName(String modeleName) { this.modeleName = modeleName; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }

    public Map<String, String> getModeleAttributes() { return modeleAttributes; }
    public void setModeleAttributes(Map<String, String> modeleAttributes) { this.modeleAttributes = modeleAttributes; }

    public List<Long> getOnduleurIds() { return onduleurIds; }
    public void setOnduleurIds(List<Long> onduleurIds) { this.onduleurIds = new ArrayList<>(onduleurIds); }
}