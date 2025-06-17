package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "societe")
public class Societe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "social_reason")
    @JsonProperty("socialReason")
    private String socialReason;

    private String language;

    @Column(name = "time_zone")
    @JsonProperty("timeZone")
    private String timeZone;

    @Temporal(TemporalType.TIMESTAMP)
    private Date license;

    @Column(name = "is_blocked")
    @JsonProperty("isBlocked")
    private Boolean isBlocked;

    private String eventType; // Reinstated eventType field

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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
}