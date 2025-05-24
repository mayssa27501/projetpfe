package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Modele;
import tn.esprit.projetkafka.query.entity.ModeleView;
import tn.esprit.projetkafka.query.repository.ModeleViewRepository;

@Component
public class ModeleEventConsumer {
    private final ModeleViewRepository queryRepository;

    @Autowired
    public ModeleEventConsumer(ModeleViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "modele-events", groupId = "modele-group")
    public void consumeModeleEvent(Modele modele) {
        ModeleView modeleView = new ModeleView();
        modeleView.setId(modele.getId());
        modeleView.setName(modele.getName());
        modeleView.setDescription(modele.getDescription());
        modeleView.setAttributes(modele.getAttributes());
        queryRepository.save(modeleView);
    }
}