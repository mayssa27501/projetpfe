package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.query.entity.OnduleurView;
import tn.esprit.projetkafka.query.repository.OnduleurViewRepository;

@Component
public class OnduleurEventConsumer {
    private final OnduleurViewRepository queryRepository;

    @Autowired
    public OnduleurEventConsumer(OnduleurViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "onduleur-events", groupId = "onduleur-group")
    public void consumeOnduleurEvent(Onduleur onduleur) {
        // Détecter une suppression si l'objet est minimal (seul id est défini)
        if (onduleur.getId() != null && onduleur.getCode() == null && onduleur.getDescription() == null) {
            queryRepository.deleteById(onduleur.getId());
            return;
        }

        OnduleurView onduleurView = new OnduleurView();
        onduleurView.setId(onduleur.getId());
        onduleurView.setCode(onduleur.getCode());
        onduleurView.setDescription(onduleur.getDescription());
        onduleurView.setIndex(onduleur.getIndex());
        onduleurView.setDateCommunication(onduleur.getDateCommunication());
        onduleurView.setHeure(onduleur.getHeure());
        onduleurView.setConsommationKwh(onduleur.getConsommationKwh());
        onduleurView.setProductionKwh(onduleur.getProductionKwh());
        onduleurView.setBloque(onduleur.getBloque());

        // Copier siteId depuis Site
        if (onduleur.getSite() != null) {
            onduleurView.setSiteId(onduleur.getSite().getId());
        } else {
            onduleurView.setSiteId(null);
        }

        // Copier localeId depuis Locale
        if (onduleur.getLocale() != null) {
            onduleurView.setLocaleId(onduleur.getLocale().getId());
        } else {
            onduleurView.setLocaleId(null);
        }

        // Copier boxId et boxCode depuis Box
        if (onduleur.getBox() != null) {
            onduleurView.setBoxId(onduleur.getBox().getId());
            onduleurView.setBoxCode(onduleur.getBox().getCode());
        } else {
            onduleurView.setBoxId(null);
            onduleurView.setBoxCode(null);
        }

        queryRepository.save(onduleurView);
    }
}