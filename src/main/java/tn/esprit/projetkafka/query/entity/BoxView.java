package tn.esprit.projetkafka.query.entity;

import jakarta.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "box_view")
public class BoxView {

    @Id
    private Long id;

    private String code;
    private String serialNumber;
    private String description;
    private Boolean isBlocked;
    private Long modeleId;
    private String modeleName;

    @ElementCollection
    @MapKeyColumn(name = "attribute_key")
    @Column(name = "attribute_value")
    @CollectionTable(name = "box_view_attributes", joinColumns = @JoinColumn(name = "box_view_id"))
    private Map<String, String> modeleAttributes = new HashMap<>();

    @ElementCollection
    @CollectionTable(name = "box_view_onduleurs", joinColumns = @JoinColumn(name = "box_view_id"))
    private List<Long> onduleurIds;

    private Long localeId;
    private String localeName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }
    public Long getModeleId() { return modeleId; }
    public void setModeleId(Long modeleId) { this.modeleId = modeleId; }
    public String getModeleName() { return modeleName; }
    public void setModeleName(String modeleName) { this.modeleName = modeleName; }
    public Map<String, String> getModeleAttributes() { return modeleAttributes; }
    public void setModeleAttributes(Map<String, String> modeleAttributes) { this.modeleAttributes = modeleAttributes; }
    public List<Long> getOnduleurIds() { return onduleurIds; }
    public void setOnduleurIds(List<Long> onduleurIds) { this.onduleurIds = onduleurIds; }
    public Long getLocaleId() { return localeId; }
    public void setLocaleId(Long localeId) { this.localeId = localeId; }
    public String getLocaleName() { return localeName; }
    public void setLocaleName(String localeName) { this.localeName = localeName; }
}