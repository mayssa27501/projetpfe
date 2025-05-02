package tn.esprit.projetkafka.command.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "box")
public class Box {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String serialNumber;
    private String description;
    private String communicationDate;
    private String communicationHour;

    @ManyToOne
    private Modele modele;

    private String firmwareVersion;
    private LocalDate installationDate;

    private int measurePeriod;
    private int sensorPeriod;
    private int outputPeriod;
    private int commandPeriod;
    private int panneVerificationPeriod;

    private boolean enPanne;
    private boolean panneAlert;
    private boolean emailAlert;
    private boolean notificationAlert;

    @Enumerated(EnumType.STRING)
    private LightingType lightingType;

    @Enumerated(EnumType.STRING)
    private TypesCommandeRelais actionneurCommandType;

    @Enumerated(EnumType.STRING)
    private TypesCommandeRelais eclairageCommandType;

    @OneToMany(mappedBy = "box")
    private List<BoxCommand> boxCommands;

    private String simNumber;
    private Boolean isBlocked;
    private String positionGps;

    private String ServerAdress;
    private String ServerPort;
    private String PS;
    private String PA;
    private String PC;
    private String PWC;
    private String ConfigModBus;
    private String ooo;
    private String apn;
    private String usr;
    private String pwd;

    // Getters & Setters
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

    public Modele getModele() { return modele; }
    public void setModele(Modele modele) { this.modele = modele; }

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

    public LightingType getLightingType() { return lightingType; }
    public void setLightingType(LightingType lightingType) { this.lightingType = lightingType; }

    public TypesCommandeRelais getActionneurCommandType() { return actionneurCommandType; }
    public void setActionneurCommandType(TypesCommandeRelais actionneurCommandType) { this.actionneurCommandType = actionneurCommandType; }

    public TypesCommandeRelais getEclairageCommandType() { return eclairageCommandType; }
    public void setEclairageCommandType(TypesCommandeRelais eclairageCommandType) { this.eclairageCommandType = eclairageCommandType; }

    public String getSimNumber() { return simNumber; }
    public void setSimNumber(String simNumber) { this.simNumber = simNumber; }

    public Boolean getIsBlocked() { return isBlocked; }
    public void setIsBlocked(Boolean isBlocked) { this.isBlocked = isBlocked; }

    public String getPositionGps() { return positionGps; }
    public void setPositionGps(String positionGps) { this.positionGps = positionGps; }

    public String getServerAdress() { return ServerAdress; }
    public void setServerAdress(String serverAdress) { ServerAdress = serverAdress; }

    public String getServerPort() { return ServerPort; }
    public void setServerPort(String serverPort) { ServerPort = serverPort; }

    public String getPS() { return PS; }
    public void setPS(String PS) { this.PS = PS; }

    public String getPA() { return PA; }
    public void setPA(String PA) { this.PA = PA; }

    public String getPC() { return PC; }
    public void setPC(String PC) { this.PC = PC; }

    public String getPWC() { return PWC; }
    public void setPWC(String PWC) { this.PWC = PWC; }

    public String getConfigModBus() { return ConfigModBus; }
    public void setConfigModBus(String configModBus) { ConfigModBus = configModBus; }

    public String getOoo() { return ooo; }
    public void setOoo(String ooo) { this.ooo = ooo; }

    public String getApn() { return apn; }
    public void setApn(String apn) { this.apn = apn; }

    public String getUsr() { return usr; }
    public void setUsr(String usr) { this.usr = usr; }

    public String getPwd() { return pwd; }
    public void setPwd(String pwd) { this.pwd = pwd; }
}
