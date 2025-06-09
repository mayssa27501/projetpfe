package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.GroupeOnduleur;

@Component
public class GroupeOnduleurEventProducer {
    private final KafkaTemplate<String, GroupeOnduleur> kafkaTemplate;

    @Autowired
    public GroupeOnduleurEventProducer(KafkaTemplate<String, GroupeOnduleur> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendGroupeOnduleurEvent(GroupeOnduleur groupeOnduleur) {
        kafkaTemplate.send("groupe-onduleur-events", groupeOnduleur);
    }
}