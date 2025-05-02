package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Box;

@Component
public class BoxEventProducer {
    private final KafkaTemplate<String, Box> kafkaTemplate;

    @Autowired
    public BoxEventProducer(KafkaTemplate<String, Box> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBoxEvent(Box box) {
        kafkaTemplate.send("box-events", box);
    }
}
