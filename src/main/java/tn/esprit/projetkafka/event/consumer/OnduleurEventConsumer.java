package tn.esprit.projetkafka.event.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.query.entity.OnduleurView;
import tn.esprit.projetkafka.query.repository.OnduleurViewRepository;

@Component
public class OnduleurEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OnduleurEventConsumer.class);
    private final OnduleurViewRepository queryRepository;

    @Autowired
    public OnduleurEventConsumer(OnduleurViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "onduleur-events", groupId = "onduleur-group")
    public void consumeOnduleurEvent(Onduleur onduleur) {
        logger.info("Received Onduleur event: id={}, code={}, boxId={}",
                onduleur.getId(),
                onduleur.getCode(),
                onduleur.getBox() != null ? onduleur.getBox().getId() : null);

        if (onduleur.getId() != null && onduleur.getCode() == null && onduleur.getDescription() == null) {
            logger.info("Deleting OnduleurView with id={}", onduleur.getId());
            queryRepository.deleteById(onduleur.getId());
            return;
        }

        OnduleurView onduleurView = new OnduleurView();
        onduleurView.setId(onduleur.getId());
        onduleurView.setCode(onduleur.getCode());
        onduleurView.setDescription(onduleur.getDescription());
        onduleurView.setIndex(onduleur.getIndex());
        onduleurView.setDateCommunication(onduleur.getDateCommunication());
        onduleurView.setHeure(onduleur.getHeure());
        onduleurView.setConsommationKwh(onduleur.getConsommationKwh());
        onduleurView.setProductionKwh(onduleur.getProductionKwh());
        onduleurView.setBloque(onduleur.getBloque());

        onduleurView.setSiteId(onduleur.getSite() != null ? onduleur.getSite().getId() : null);
//        onduleurView.setLocaleId(onduleur.getLocale() != null ? onduleur.getLocale().getId() : null);
        onduleurView.setBoxId(onduleur.getBox() != null ? onduleur.getBox().getId() : null);
        onduleurView.setGroupeOnduleurId(onduleur.getGroupeOnduleur() != null ? onduleur.getGroupeOnduleur().getId() : null);

        try {
            queryRepository.save(onduleurView);
            logger.info("Saved OnduleurView with id={}", onduleurView.getId());
        } catch (Exception e) {
            logger.error("Failed to save OnduleurView with id={}: {}", onduleurView.getId(), e.getMessage(), e);
        }
    }
}