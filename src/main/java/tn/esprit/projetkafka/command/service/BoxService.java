package tn.esprit.projetkafka.command.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.command.entity.Modele;
import tn.esprit.projetkafka.command.repository.BoxRepository;
import tn.esprit.projetkafka.command.repository.ModeleRepository;
import tn.esprit.projetkafka.event.producer.BoxEventProducer;

@Service
public class BoxService {
    private static final Logger logger = LoggerFactory.getLogger(BoxService.class);
    private final BoxRepository repository;
    private final ModeleRepository modeleRepository;
    private final BoxEventProducer eventProducer;

    public BoxService(BoxRepository repository, ModeleRepository modeleRepository, BoxEventProducer eventProducer) {
        this.repository = repository;
        this.modeleRepository = modeleRepository;
        this.eventProducer = eventProducer;
    }

    @Transactional
    public Box createBox(Box box) {
        logger.info("Creating box with code: {}", box.getCode());
        if (box.getModele() != null && box.getModele().getId() != null) {
            Modele fullModele = modeleRepository.findById(box.getModele().getId())
                    .orElseThrow(() -> {
                        logger.error("Modele not found with ID: {}", box.getModele().getId());
                        return new RuntimeException("Modele not found with ID: " + box.getModele().getId());
                    });
            box.setModele(fullModele);
        } else {
            logger.warn("No modele provided for box creation");
        }
        logger.debug("Saving box with modeleAttributes: {}", box.getModeleAttributes());
        Box savedBox = repository.save(box);
        logger.info("Box created with ID: {}", savedBox.getId());
        eventProducer.sendBoxEvent(savedBox);
        return savedBox;
    }

    @Transactional
    public Box updateBox(Long id, Box updatedBox) {
        logger.info("Updating box with ID: {}. Incoming modeleAttributes: {}", id, updatedBox.getModeleAttributes());
        return repository.findById(id).map(existingBox -> {
            if (updatedBox.getModele() != null && updatedBox.getModele().getId() != null) {
                Modele fullModele = modeleRepository.findById(updatedBox.getModele().getId())
                        .orElseThrow(() -> {
                            logger.error("Modele not found with ID: {}", updatedBox.getModele().getId());
                            return new RuntimeException("Modele not found with ID: " + updatedBox.getModele().getId());
                        });
                existingBox.setModele(fullModele);
            } else {
                logger.warn("No modele provided for box ID: {}", id);
            }
            existingBox.setCode(updatedBox.getCode());
            existingBox.setSerialNumber(updatedBox.getSerialNumber());
            existingBox.setDescription(updatedBox.getDescription());
            existingBox.setIsBlocked(updatedBox.getIsBlocked());
            if (updatedBox.getModeleAttributes() != null) {
                logger.debug("Updating modeleAttributes from: {} to: {}",
                        existingBox.getModeleAttributes(), updatedBox.getModeleAttributes());
                existingBox.getModeleAttributes().clear();
                existingBox.getModeleAttributes().putAll(updatedBox.getModeleAttributes());
            } else {
                logger.warn("No modeleAttributes provided for box ID: {}", id);
                existingBox.getModeleAttributes().clear();
            }
            // Note: Onduleurs are managed via OnduleurService, so no direct update here
            logger.debug("Saving box with updated modeleAttributes: {}", existingBox.getModeleAttributes());
            Box saved = repository.save(existingBox);
            logger.info("Box updated with ID: {}. Final modeleAttributes: {}", saved.getId(), saved.getModeleAttributes());
            eventProducer.sendBoxEvent(saved);
            return saved;
        }).orElseThrow(() -> {
            logger.error("Box not found with ID: {}", id);
            return new RuntimeException("Box not found with ID: " + id);
        });
    }

    @Transactional
    public void deleteBox(Long id) {
        if (!repository.existsById(id)) {
            logger.error("Box not found with ID: {}", id);
            throw new RuntimeException("Box not found with ID: " + id);
        }
        logger.info("Deleting box with ID: {}", id);
        repository.deleteById(id);
        Box deletedBox = new Box();
        deletedBox.setId(id);
        deletedBox.setEventType("DELETE");
        eventProducer.sendBoxEvent(deletedBox);
        logger.info("Box deleted with ID: {}", id);
    }
}