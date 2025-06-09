package tn.esprit.projetkafka.event.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Onduleur;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OnduleurEventProducer {
    private static final Logger logger = LoggerFactory.getLogger(OnduleurEventProducer.class);
    private static final String TOPIC = "onduleur-events";

    private final KafkaTemplate<String, Onduleur> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public OnduleurEventProducer(KafkaTemplate<String, Onduleur> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOnduleurEvent(Onduleur onduleur) {
        try {
            String json = objectMapper.writeValueAsString(onduleur);
            logger.info("Serialized Onduleur event: {}", json);
            logger.info("Sending Onduleur event: id={}, code={}, boxId={}",
                    onduleur.getId(),
                    onduleur.getCode(),
                    onduleur.getBox() != null ? onduleur.getBox().getId() : null);
            kafkaTemplate.send(TOPIC, onduleur);
        } catch (Exception e) {
            logger.error("Failed to serialize Onduleur event for id {}: {}", onduleur.getId(), e.getMessage(), e);
        }
    }
}