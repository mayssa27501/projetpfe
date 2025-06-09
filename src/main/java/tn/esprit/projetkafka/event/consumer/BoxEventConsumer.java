package tn.esprit.projetkafka.event.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.command.entity.Onduleur; // Importation ajoutée
import tn.esprit.projetkafka.query.entity.BoxView;
import tn.esprit.projetkafka.query.repository.BoxViewRepository;
import tn.esprit.projetkafka.query.repository.OnduleurViewRepository;

import java.util.stream.Collectors;

@Component
public class BoxEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(BoxEventConsumer.class);
    private final BoxViewRepository queryRepository;
    private final OnduleurViewRepository onduleurViewRepository;

    @Autowired
    public BoxEventConsumer(BoxViewRepository queryRepository, OnduleurViewRepository onduleurViewRepository) {
        this.queryRepository = queryRepository;
        this.onduleurViewRepository = onduleurViewRepository;
    }

    @KafkaListener(topics = "box-events", groupId = "box-group")
    public void consumeBoxEvent(Box box) {
        logger.info("Received Box event: id={}, code={}, eventType={}", box.getId(), box.getCode(), box.getEventType());

        if ("DELETE".equalsIgnoreCase(box.getEventType())) {
            queryRepository.deleteById(box.getId());
            logger.info("Deleted BoxView with id={}", box.getId());
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

        // Update onduleurIds
        boxView.setOnduleurIds(box.getOnduleurs().stream()
                .map(Onduleur::getId)
                .collect(Collectors.toList()));

        try {
            queryRepository.save(boxView);
            logger.info("Saved BoxView with id={}", boxView.getId());
        } catch (Exception e) {
            logger.error("Failed to save BoxView with id={}: {}", boxView.getId(), e.getMessage(), e);
        }
    }
}