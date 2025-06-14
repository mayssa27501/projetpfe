package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.GroupeOnduleur;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.query.entity.GroupeOnduleurView;
import tn.esprit.projetkafka.query.entity.OnduleurView;
import tn.esprit.projetkafka.query.repository.GroupeOnduleurViewRepository;
import tn.esprit.projetkafka.query.repository.OnduleurViewRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupeOnduleurEventConsumer {
    private final GroupeOnduleurViewRepository groupeRepository;
    private final OnduleurViewRepository onduleurRepository;

    @Autowired
    public GroupeOnduleurEventConsumer(GroupeOnduleurViewRepository groupeRepository, OnduleurViewRepository onduleurRepository) {
        this.groupeRepository = groupeRepository;
        this.onduleurRepository = onduleurRepository;
    }

    @KafkaListener(topics = "groupe-onduleur-events", groupId = "groupe-onduleur-group")
    public void consumeGroupeOnduleurEvent(GroupeOnduleur groupeOnduleur) {
        if ("DELETE".equalsIgnoreCase(groupeOnduleur.getEventType())) {
            groupeRepository.deleteById(groupeOnduleur.getId());
            onduleurRepository.deleteByGroupeOnduleurId(groupeOnduleur.getId());
            return;
        }

        GroupeOnduleurView groupeView = new GroupeOnduleurView();
        groupeView.setId(groupeOnduleur.getId());
        groupeView.setCode(groupeOnduleur.getCode());
        groupeView.setDescription(groupeOnduleur.getDescription());
        groupeView.setOperation(groupeOnduleur.getOperation());
        groupeView.setBloque(groupeOnduleur.getBloque());
        groupeView.setSiteId(groupeOnduleur.getSite() != null ? groupeOnduleur.getSite().getId() : null);

        // Mettre à jour les OnduleurView associés
        List<OnduleurView> onduleurViews = new ArrayList<>();
        if (groupeOnduleur.getOnduleurs() != null) {
            for (Onduleur onduleur : groupeOnduleur.getOnduleurs()) {
                OnduleurView onduleurView = new OnduleurView();
                onduleurView.setId(onduleur.getId());
                onduleurView.setCode(onduleur.getCode());
                onduleurView.setDescription(onduleur.getDescription());
                onduleurView.setSiteId(onduleur.getSite() != null ? onduleur.getSite().getId() : null);
                onduleurView.setBoxId(onduleur.getBox() != null ? onduleur.getBox().getId() : null);
                onduleurView.setGroupeOnduleurId(groupeOnduleur.getId());
                onduleurView.setIndex(onduleur.getIndex());
                onduleurView.setDateCreation(onduleur.getDateCreation());
                onduleurView.setHeure(onduleur.getHeure());
                onduleurView.setConsommationKwh(onduleur.getConsommationKwh());
                onduleurView.setProductionKwh(onduleur.getProductionKwh());
                onduleurView.setBloque(onduleur.getBloque());
                onduleurView.setAdresse(onduleur.getAdresse());
                onduleurView.setCourt(onduleur.getCourt());
                onduleurView.setCommunication(onduleur.getCommunication());
                onduleurView.setModeleOnduleur(onduleur.getModeleOnduleur() != null ? onduleur.getModeleOnduleur().name() : null);
                onduleurView.setType(onduleur.getType() != null ? onduleur.getType().name() : null);
                onduleurView.setMultiplicateur(onduleur.getMultiplicateur());
                onduleurViews.add(onduleurView);
            }
            onduleurRepository.saveAll(onduleurViews);
        }
        groupeView.setOnduleurs(onduleurViews);

        groupeRepository.save(groupeView);
    }
}