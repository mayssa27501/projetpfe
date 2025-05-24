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
        if ("DELETE".equalsIgnoreCase(box.getEventType())) {
            queryRepository.deleteById(box.getId());
            return;
        }

        BoxView boxView = new BoxView();
        boxView.setId(box.getId());
        boxView.setCode(box.getCode());
        boxView.setSerialNumber(box.getSerialNumber());
        boxView.setDescription(box.getDescription());
        boxView.setIsBlocked(box.getIsBlocked());

        // Copy Modele information
        if (box.getModele() != null) {
            boxView.setModeleId(box.getModele().getId());
            boxView.setModeleName(box.getModele().getName());
            boxView.setModeleAttributes(box.getModele().getAttributes());
        } else {
            boxView.setModeleId(null);
            boxView.setModeleName(null);
            boxView.setModeleAttributes(new java.util.HashMap<>());
        }

        queryRepository.save(boxView);
    }
}