package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Site;

@Component
public class SiteEventProducer {
    private final KafkaTemplate<String, Site> kafkaTemplate;

    @Autowired
    public SiteEventProducer(KafkaTemplate<String, Site> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSiteEvent(Site site) {
        kafkaTemplate.send("site-events", site);
    }
}