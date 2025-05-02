package tn.esprit.projetkafka.query.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;


@Table(name = "box_view")
public class BoxView {

    @Id
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("serialNumber")
    private String serialNumber;

    @JsonProperty("description")
    private String description;

    @JsonProperty("communicationDate")
    private String communicationDate;

    @JsonProperty("communicationHour")
    private String communicationHour;

    @JsonProperty("firmwareVersion")
    private String firmwareVersion;

    @JsonProperty("installationDate")
    private LocalDate installationDate;

    @JsonProperty("measurePeriod")
    private int measurePeriod;

    @JsonProperty("sensorPeriod")
    private int sensorPeriod;

    @JsonProperty("outputPeriod")
    private int outputPeriod;

    @JsonProperty("commandPeriod")
    private int commandPeriod;

    @JsonProperty("panneVerificationPeriod")
    private int panneVerificationPeriod;

    @JsonProperty("enPanne")
    private boolean enPanne;

    @JsonProperty("panneAlert")
    private boolean panneAlert;

    @JsonProperty("emailAlert")
    private boolean emailAlert;

    @JsonProperty("notificationAlert")
    private boolean notificationAlert;

    @JsonProperty("lightingType")
    private String lightingType;

    @JsonProperty("actionneurCommandType")
    private String actionneurCommandType;

    @JsonProperty("eclairageCommandType")
    private String eclairageCommandType;

    @JsonProperty("simNumber")
    private String simNumber;

    @JsonProperty("isBlocked")
    private Boolean isBlocked;

    @JsonProperty("positionGps")
    private String positionGps;

    @JsonProperty("serverAdress")
    private String serverAdress;

    @JsonProperty("serverPort")
    private String serverPort;

    @JsonProperty("PS")
    private String PS;

    @JsonProperty("PA")
    private String PA;

    @JsonProperty("PC")
    private String PC;

    @JsonProperty("PWC")
    private String PWC;

    @JsonProperty("configModBus")
    private String configModBus;

    @JsonProperty("ooo")
    private String ooo;

    @JsonProperty("apn")
    private String apn;

    @JsonProperty("usr")
    private String usr;

    @JsonProperty("pwd")
    private String pwd;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCommunicationDate() { return communicationDate; }
    public void setCommunicationDate(String communicationDate) { this.communicationDate = communicationDate; }

    public String getCommunicationHour() { return communicationHour; }
    public void setCommunicationHour(String communicationHour) { this.communicationHour = communicationHour; }

    public String getFirmwareVersion() { return firmwareVersion; }
    public void setFirmwareVersion(String firmwareVersion) { this.firmwareVersion = firmwareVersion; }

    public LocalDate getInstallationDate() { return installationDate; }
    public void setInstallationDate(LocalDate installationDate) { this.installationDate = installationDate; }

    public int getMeasurePeriod() { return measurePeriod; }
    public void setMeasurePeriod(int measurePeriod) { this.measurePeriod = measurePeriod; }

    public int getSensorPeriod() { return sensorPeriod; }
    public void setSensorPeriod(int sensorPeriod) { this.sensorPeriod = sensorPeriod; }

    public int getOutputPeriod() { return outputPeriod; }
    public void setOutputPeriod(int outputPeriod) { this.outputPeriod = outputPeriod; }

    public int getCommandPeriod() { return commandPeriod; }
    public void setCommandPeriod(int commandPeriod) { this.commandPeriod = commandPeriod; }

    public int getPanneVerificationPeriod() { return panneVerificationPeriod; }
    public void setPanneVerificationPeriod(int panneVerificationPeriod) { this.panneVerificationPeriod = panneVerificationPeriod; }

    public boolean isEnPanne() { return enPanne; }
    public void setEnPanne(boolean enPanne) { this.enPanne = enPanne; }

    public boolean isPanneAlert() { return panneAlert; }
    public void setPanneAlert(boolean panneAlert) { this.panneAlert = panneAlert; }

    public boolean isEmailAlert() { return emailAlert; }
    public void setEmailAlert(boolean emailAlert) { this.emailAlert = emailAlert; }

    public boolean isNotificationAlert() { return notificationAlert; }
    public void setNotificationAlert(boolean notificationAlert) { this.notificationAlert = notificationAlert; }

    public String getLightingType() { return lightingType; }
    public void setLightingType(String lightingType) { this.lightingType = lightingType; }

    public String getActionneurCommandType() { return actionneurCommandType; }
    public void setActionneurCommandType(String actionneurCommandType) { this.actionneurCommandType = actionneurCommandType; }

    public String getEclairageCommandType() { return eclairageCommandType; }
    public void setEclairageCommandType(String eclairageCommandType) { this.eclairageCommandType = eclairageCommandType; }

    public String getSimNumber() { return simNumber; }
    public void setSimNumber(String simNumber) { this.simNumber = simNumber; }

    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }

    public String getPositionGps() { return positionGps; }
    public void setPositionGps(String positionGps) { this.positionGps = positionGps; }

    public String getServerAdress() { return serverAdress; }
    public void setServerAdress(String serverAdress) { this.serverAdress = serverAdress; }

    public String getServerPort() { return serverPort; }
    public void setServerPort(String serverPort) { this.serverPort = serverPort; }

    public String getPS() { return PS; }
    public void setPS(String PS) { this.PS = PS; }

    public String getPA() { return PA; }
    public void setPA(String PA) { this.PA = PA; }

    public String getPC() { return PC; }
    public void setPC(String PC) { this.PC = PC; }

    public String getPWC() { return PWC; }
    public void setPWC(String PWC) { this.PWC = PWC; }

    public String getConfigModBus() { return configModBus; }
    public void setConfigModBus(String configModBus) { this.configModBus = configModBus; }

    public String getOoo() { return ooo; }
    public void setOoo(String ooo) { this.ooo = ooo; }

    public String getApn() { return apn; }
    public void setApn(String apn) { this.apn = apn; }

    public String getUsr() { return usr; }
    public void setUsr(String usr) { this.usr = usr; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
}
