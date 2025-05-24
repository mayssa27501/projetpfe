package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Onduleur;

@Component
public class OnduleurEventProducer {
    private final KafkaTemplate<String, Onduleur> kafkaTemplate;

    @Autowired
    public OnduleurEventProducer(KafkaTemplate<String, Onduleur> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOnduleurEvent(Onduleur onduleur) {
        kafkaTemplate.send("onduleur-events", onduleur);
    }
}