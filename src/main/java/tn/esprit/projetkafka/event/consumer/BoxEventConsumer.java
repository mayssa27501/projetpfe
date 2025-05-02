package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.query.entity.BoxView;
import tn.esprit.projetkafka.query.repository.BoxViewRepository;

@Component
public class BoxEventConsumer {
    private final BoxViewRepository queryRepository;

    @Autowired
    public BoxEventConsumer(BoxViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "box-events", groupId = "box-group")
    public void consumeBoxEvent(Box box) {
        BoxView boxView = new BoxView();
        boxView.setId(box.getId());
        boxView.setCode(box.getCode());
        boxView.setSerialNumber(box.getSerialNumber());
        boxView.setDescription(box.getDescription());
        boxView.setCommunicationDate(box.getCommunicationDate());
        boxView.setCommunicationHour(box.getCommunicationHour());
        boxView.setFirmwareVersion(box.getFirmwareVersion());
        boxView.setInstallationDate(box.getInstallationDate());
        boxView.setMeasurePeriod(box.getMeasurePeriod());
        boxView.setSensorPeriod(box.getSensorPeriod());
        boxView.setOutputPeriod(box.getOutputPeriod());
        boxView.setCommandPeriod(box.getCommandPeriod());
        boxView.setPanneVerificationPeriod(box.getPanneVerificationPeriod());
        boxView.setEnPanne(box.isEnPanne());
        boxView.setPanneAlert(box.isPanneAlert());
        boxView.setEmailAlert(box.isEmailAlert());
        boxView.setNotificationAlert(box.isNotificationAlert());
//        boxView.setLightingType(box.getLightingType());
//        boxView.setActionneurCommandType(box.getActionneurCommandType());
//        boxView.setEclairageCommandType(box.getEclairageCommandType());
        boxView.setSimNumber(box.getSimNumber());
        boxView.setIsBlocked(box.getIsBlocked());
        boxView.setPositionGps(box.getPositionGps());
        boxView.setServerAdress(box.getServerAdress());
        boxView.setServerPort(box.getServerPort());
        boxView.setPS(box.getPS());
        boxView.setPA(box.getPA());
        boxView.setPC(box.getPC());
        boxView.setPWC(box.getPWC());
        boxView.setConfigModBus(box.getConfigModBus());
        boxView.setOoo(box.getOoo());
        boxView.setApn(box.getApn());
        boxView.setUsr(box.getUsr());
        boxView.setPwd(box.getPwd());

        queryRepository.save(boxView);
    }
}
