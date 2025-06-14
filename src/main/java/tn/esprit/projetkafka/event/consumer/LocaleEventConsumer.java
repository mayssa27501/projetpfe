package tn.esprit.projetkafka.event.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Locale;
import tn.esprit.projetkafka.query.entity.LocaleView;
import tn.esprit.projetkafka.query.repository.LocaleViewRepository;

@Component
public class LocaleEventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(LocaleEventConsumer.class);
    private final LocaleViewRepository queryRepository;

    @Autowired
    public LocaleEventConsumer(LocaleViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "locale-events", groupId = "locale-group")
    public void consumeLocaleEvent(Locale locale) {
        logger.info("Received Locale event: id={}, code={}", locale.getId(), locale.getCode());

        LocaleView localeView = new LocaleView();
        localeView.setId(locale.getId());
        localeView.setCode(locale.getCode());
        localeView.setName(locale.getName());
        localeView.setDescription(locale.getDescription());
        localeView.setBlocked(locale.getBlocked());

        try {
            queryRepository.save(localeView);
            logger.info("Saved LocaleView with id={}", localeView.getId());
        } catch (Exception e) {
            logger.error("Failed to save LocaleView with id={}: {}", localeView.getId(), e.getMessage(), e);
        }
    }
}