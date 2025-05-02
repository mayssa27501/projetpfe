package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Modele;

@Component
public class ModeleEventProducer {
    private final KafkaTemplate<String, Modele> kafkaTemplate;

    @Autowired
    public ModeleEventProducer(KafkaTemplate<String, Modele> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendModeleEvent(Modele modele) {
        kafkaTemplate.send("modele-events", modele);
    }
}
