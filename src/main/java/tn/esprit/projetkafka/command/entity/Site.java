package tn.esprit.projetkafka.command.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "site")
public class Site {

    @Id
    private Long id;

    private String description;
    private String address;
    private String timeZone;
    private String positionGps;
    private Boolean isBlocked;
    private Boolean isPrincipal;

//    @ManyToOne
//    @JoinColumn(name = "society_id", nullable = false)
//    private Society society;
//
//    @OneToMany(mappedBy = "site")
//    @JsonIgnore
//    private List<Locale> listLocale;
//
//    @OneToMany(mappedBy = "site")
//    @JsonIgnore
//    private List<CounterGroup> counterGroups;
//
//    @OneToMany(mappedBy = "site")
//    @JsonIgnore
//    private List<SensorGroup> sensorGroup;
//
//    @OneToMany(mappedBy = "site")
//    @JsonIgnore
//    private List<Schedule> schedules;
//
//    @OneToMany(mappedBy = "site")
//    @JsonIgnore
//    private List<ActuatorGroup> actuatorGroup;
//
    @OneToMany(mappedBy = "site")
    @JsonIgnore
    private List<GroupeOnduleur> groupeOnduleurs;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getPositionGps() {
        return positionGps;
    }

    public void setPositionGps(String positionGps) {
        this.positionGps = positionGps;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Boolean getIsPrincipal() {
        return isPrincipal;
    }

    public void setIsPrincipal(Boolean isPrincipal) {
        this.isPrincipal = isPrincipal;
    }

//    public Society getSociety() {
//        return society;
//    }
//
//    public void setSociety(Society society) {
//        this.society = society;
//    }
//
//    public List<Locale> getListLocale() {
//        return listLocale;
//    }
//
//    public void setListLocale(List<Locale> listLocale) {
//        this.listLocale = listLocale;
//    }
//
//    public List<CounterGroup> getCounterGroups() {
//        return counterGroups;
//    }
//
//    public void setCounterGroups(List<CounterGroup> counterGroups) {
//        this.counterGroups = counterGroups;
//    }
//
//    public List<SensorGroup> getSensorGroup() {
//        return sensorGroup;
//    }
//
//    public void setSensorGroup(List<SensorGroup> sensorGroup) {
//        this.sensorGroup = sensorGroup;
//    }
//
//    public List<Schedule> getSchedules() {
//        return schedules;
//    }
//
//    public void setSchedules(List<Schedule> schedules) {
//        this.schedules = schedules;
//    }
//
//    public List<ActuatorGroup> getActuatorGroup() {
//        return actuatorGroup;
//    }
//
//    public void setActuatorGroup(List<ActuatorGroup> actuatorGroup) {
//        this.actuatorGroup = actuatorGroup;
//    }

    public List<GroupeOnduleur> getGroupeOnduleurs() {
        return groupeOnduleurs;
    }

    public void setGroupeOnduleurs(List<GroupeOnduleur> groupeOnduleurs) {
        this.groupeOnduleurs = groupeOnduleurs;
    }
}