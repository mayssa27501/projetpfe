package tn.esprit.projetkafka.command.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.projetkafka.command.entity.Societe;
import tn.esprit.projetkafka.command.repository.SocieteRepository;
import tn.esprit.projetkafka.event.producer.SocieteEventProducer;

@Service
public class SocieteService {
    private static final Logger logger = LoggerFactory.getLogger(SocieteService.class);
    private final SocieteRepository repository;
    private final SocieteEventProducer eventProducer;

    public SocieteService(SocieteRepository repository, SocieteEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    @Transactional
    public Societe createSociete(Societe societe) {
        logger.info("Creating societe with social reason: {}", societe.getSocialReason());
        societe.setEventType("CREATE");
        logger.debug("Saving societe with details: {}", societe);
        Societe savedSociete = repository.save(societe);
        logger.info("Societe created with ID: {}", savedSociete.getId());
        eventProducer.sendSocieteEvent(savedSociete);
        return savedSociete;
    }

    @Transactional
    public Societe updateSociete(Long id, Societe updatedSociete) {
        logger.info("Updating societe with ID: {}", id);
        return repository.findById(id).map(existingSociete -> {
            existingSociete.setSocialReason(updatedSociete.getSocialReason());
            existingSociete.setLanguage(updatedSociete.getLanguage());
            existingSociete.setTimeZone(updatedSociete.getTimeZone());
            existingSociete.setLicense(updatedSociete.getLicense());
            existingSociete.setIsBlocked(updatedSociete.getIsBlocked());
            existingSociete.setEventType("UPDATE");
            logger.debug("Saving societe with updated details: {}", existingSociete);
            Societe saved = repository.save(existingSociete);
            logger.info("Societe updated with ID: {}", saved.getId());
            eventProducer.sendSocieteEvent(saved);
            return saved;
        }).orElseThrow(() -> {
            logger.error("Societe not found with ID: {}", id);
            return new RuntimeException("Societe not found with ID: " + id);
        });
    }

    @Transactional
    public void deleteSociete(Long id) {
        if (!repository.existsById(id)) {
            logger.error("Societe not found with ID: {}", id);
            throw new RuntimeException("Societe not found with ID: " + id);
        }
        logger.info("Deleting societe with ID: {}", id);
        repository.deleteById(id);
        Societe deletedSociete = new Societe();
        deletedSociete.setId(id);
        deletedSociete.setEventType("DELETE");
        eventProducer.sendSocieteEvent(deletedSociete);
        logger.info("Societe deleted with ID: {}", id);
    }
}