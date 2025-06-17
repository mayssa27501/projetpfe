package tn.esprit.projetkafka.query.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Societe;
import tn.esprit.projetkafka.query.entity.SocieteView;
import tn.esprit.projetkafka.query.repository.SocieteViewRepository;

@Component
public class SocieteEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SocieteEventConsumer.class);
    private final SocieteViewRepository repository;

    public SocieteEventConsumer(SocieteViewRepository repository) {
        this.repository = repository;
    }

    @KafkaListener(topics = "societe-events", groupId = "societe-query-group")
    public void consumeSocieteEvent(Societe societe) {
        logger.info("Received societe event for ID: {}", societe.getId());
        if ("DELETE".equals(societe.getEventType())) {
            logger.info("Deleting societe view with ID: {}", societe.getId());
            repository.deleteById(societe.getId());
        } else {
            SocieteView societeView = new SocieteView();
            societeView.setId(societe.getId());
            societeView.setSocialReason(societe.getSocialReason());
            societeView.setLanguage(societe.getLanguage());
            societeView.setTimeZone(societe.getTimeZone());
            societeView.setLicense(societe.getLicense());
            societeView.setIsBlocked(societe.getIsBlocked());
            repository.save(societeView);
            logger.info("Saved societe view with ID: {}", societe.getId());
        }
    }
}