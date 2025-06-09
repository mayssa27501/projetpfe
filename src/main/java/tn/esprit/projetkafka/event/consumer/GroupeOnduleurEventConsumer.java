package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.GroupeOnduleur;
import tn.esprit.projetkafka.query.entity.GroupeOnduleurView;
import tn.esprit.projetkafka.query.repository.GroupeOnduleurViewRepository;

@Component
public class GroupeOnduleurEventConsumer {
    private final GroupeOnduleurViewRepository queryRepository;

    @Autowired
    public GroupeOnduleurEventConsumer(GroupeOnduleurViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "groupe-onduleur-events", groupId = "groupe-onduleur-group")
    public void consumeGroupeOnduleurEvent(GroupeOnduleur groupeOnduleur) {
        if ("DELETE".equalsIgnoreCase(groupeOnduleur.getEventType())) {
            queryRepository.deleteById(groupeOnduleur.getId());
            return;
        }

        GroupeOnduleurView groupeView = new GroupeOnduleurView();
        groupeView.setId(groupeOnduleur.getId());
        groupeView.setCode(groupeOnduleur.getCode());
        groupeView.setDescription(groupeOnduleur.getDescription());
        groupeView.setOperation(groupeOnduleur.getOperation());
        groupeView.setBloque(groupeOnduleur.getBloque());

        // Copy Site information
        if (groupeOnduleur.getSite() != null) {
            groupeView.setSiteId(groupeOnduleur.getSite().getId());

        } else {
            groupeView.setSiteId(null);

        }

        queryRepository.save(groupeView);
    }
}