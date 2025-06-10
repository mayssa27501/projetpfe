package tn.esprit.projetkafka.command.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.GroupeOnduleur;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.command.entity.Site;
import tn.esprit.projetkafka.command.repository.GroupeOnduleurRepository;
import tn.esprit.projetkafka.command.repository.OnduleurRepository;
import tn.esprit.projetkafka.command.repository.SiteRepository;
import tn.esprit.projetkafka.event.producer.GroupeOnduleurEventProducer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupeOnduleurService {
    private static final Logger logger = LoggerFactory.getLogger(GroupeOnduleurService.class);
    private final GroupeOnduleurRepository repository;
    private final SiteRepository siteRepository;
    private final OnduleurRepository onduleurRepository;
    private final GroupeOnduleurEventProducer eventProducer;

    public GroupeOnduleurService(GroupeOnduleurRepository repository, SiteRepository siteRepository,
                                 OnduleurRepository onduleurRepository, GroupeOnduleurEventProducer eventProducer) {
        this.repository = repository;
        this.siteRepository = siteRepository;
        this.onduleurRepository = onduleurRepository;
        this.eventProducer = eventProducer;
    }

    public GroupeOnduleur createGroupeOnduleur(GroupeOnduleur groupeOnduleur, List<Long> onduleurIds) {
        logger.info("Creating groupe onduleur with code: {}", groupeOnduleur.getCode());
        if (groupeOnduleur.getSite() != null && groupeOnduleur.getSite().getId() != null) {
            Site fullSite = siteRepository.findById(groupeOnduleur.getSite().getId())
                    .orElseThrow(() -> {
                        logger.error("Site not found with ID: {}", groupeOnduleur.getSite().getId());
                        return new RuntimeException("Site non trouvé avec l'ID : " + groupeOnduleur.getSite().getId());
                    });
            groupeOnduleur.setSite(fullSite);
        } else {
            logger.warn("No site provided for groupe onduleur creation");
        }

        // Affecter les onduleurs
        if (onduleurIds != null && !onduleurIds.isEmpty()) {
            List<Onduleur> onduleurs = onduleurRepository.findAllById(onduleurIds);
            if (onduleurs.size() != onduleurIds.size()) {
                logger.error("Some onduleurs not found for IDs: {}", onduleurIds);
                throw new RuntimeException("Certains onduleurs n'ont pas été trouvés");
            }
            groupeOnduleur.assignOnduleurs(onduleurs);
        }

        groupeOnduleur.setEventType("CREATE");
        GroupeOnduleur savedGroupe = repository.save(groupeOnduleur);
        logger.info("Groupe onduleur created with ID: {}", savedGroupe.getId());
        eventProducer.sendGroupeOnduleurEvent(savedGroupe);
        return savedGroupe;
    }

    public GroupeOnduleur updateGroupeOnduleur(Long id, GroupeOnduleur updatedGroupe, List<Long> onduleurIds) {
        logger.info("Updating groupe onduleur with ID: {}", id);
        return repository.findById(id).map(existingGroupe -> {
            if (updatedGroupe.getSite() != null && updatedGroupe.getSite().getId() != null) {
                Site fullSite = siteRepository.findById(updatedGroupe.getSite().getId())
                        .orElseThrow(() -> {
                            logger.error("Site not found with ID: {}", updatedGroupe.getSite().getId());
                            return new RuntimeException("Site non trouvé avec l'ID : " + updatedGroupe.getSite().getId());
                        });
                existingGroupe.setSite(fullSite);
            } else {
                logger.warn("No site provided for groupe onduleur ID: {}", id);
            }

            // Mettre à jour les champs
            existingGroupe.setCode(updatedGroupe.getCode());
            existingGroupe.setDescription(updatedGroupe.getDescription());
            existingGroupe.setOperation(updatedGroupe.getOperation());
            existingGroupe.setBloque(updatedGroupe.getBloque());

            // Mettre à jour les onduleurs
            if (onduleurIds != null) {
                List<Onduleur> onduleurs = onduleurRepository.findAllById(onduleurIds);
                if (onduleurs.size() != onduleurIds.size()) {
                    logger.error("Some onduleurs not found for IDs: {}", onduleurIds);
                    throw new RuntimeException("Certains onduleurs n'ont pas été trouvés");
                }
                existingGroupe.assignOnduleurs(onduleurs);
            }

            existingGroupe.setEventType("UPDATE");
            GroupeOnduleur saved = repository.save(existingGroupe);
            logger.info("Groupe onduleur updated with ID: {}", saved.getId());
            eventProducer.sendGroupeOnduleurEvent(saved);
            return saved;
        }).orElseThrow(() -> {
            logger.error("Groupe onduleur not found with ID: {}", id);
            return new RuntimeException("Groupe onduleur not found with id: " + id);
        });
    }

    public void deleteGroupeOnduleur(Long id) {
        if (!repository.existsById(id)) {
            logger.error("Groupe onduleur not found with ID: {}", id);
            throw new RuntimeException("Groupe onduleur not found with id: " + id);
        }
        logger.info("Deleting groupe onduleur with ID: {}", id);
        repository.deleteById(id);
        GroupeOnduleur deletedGroupe = new GroupeOnduleur();
        deletedGroupe.setId(id);
        deletedGroupe.setEventType("DELETE");
        eventProducer.sendGroupeOnduleurEvent(deletedGroupe);
        logger.info("Groupe onduleur deleted with ID: {}", id);
    }
}