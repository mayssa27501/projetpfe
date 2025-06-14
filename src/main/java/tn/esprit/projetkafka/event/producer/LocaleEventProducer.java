package tn.esprit.projetkafka.event.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Locale;

@Component
public class LocaleEventProducer {
    private static final String TOPIC = "locale-events";

    private final KafkaTemplate<String, Locale> kafkaTemplate;

    @Autowired
    public LocaleEventProducer(KafkaTemplate<String, Locale> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLocaleEvent(Locale locale) {
        kafkaTemplate.send(TOPIC, locale.getId().toString(), locale);
    }
}