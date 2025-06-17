package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Societe;

@Component
public class SocieteEventProducer {
    private final KafkaTemplate<String, Societe> kafkaTemplate;

    @Autowired
    public SocieteEventProducer(KafkaTemplate<String, Societe> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSocieteEvent(Societe societe) {
        kafkaTemplate.send("societe-events", societe);
    }
}