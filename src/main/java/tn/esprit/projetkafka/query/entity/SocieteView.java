package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "societe_view")
public class SocieteView {

    @Id
    private Long id;

    @JsonProperty("socialReason")
    private String socialReason;

    @JsonProperty("language")
    private String language;

    @JsonProperty("timeZone")
    private String timeZone;

    @JsonProperty("license")
    private Date license;

    @JsonProperty("isBlocked")
    private Boolean isBlocked;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSocialReason() {
        return socialReason;
    }

    public void setSocialReason(String socialReason) {
        this.socialReason = socialReason;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Date getLicense() {
        return license;
    }

    public void setLicense(Date license) {
        this.license = license;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }
}