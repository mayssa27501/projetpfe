package tn.esprit.projetkafka.command.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.command.repository.BoxRepository;
import tn.esprit.projetkafka.event.producer.BoxEventProducer;

@Service
public class BoxService {
    private final BoxRepository repository;
    private final BoxEventProducer eventProducer;

    public BoxService(BoxRepository repository, BoxEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Box createBox(Box box) {
        Box savedBox = repository.save(box);
        eventProducer.sendBoxEvent(savedBox);
        return savedBox;
    }

    public Box updateBox(Long id, Box updatedBox) {
        return repository.findById(id).map(existingBox -> {
            // Exemple de mise à jour, à adapter selon les champs importants
            existingBox.setCode(updatedBox.getCode());
            existingBox.setSerialNumber(updatedBox.getSerialNumber());
            existingBox.setDescription(updatedBox.getDescription());
            existingBox.setFirmwareVersion(updatedBox.getFirmwareVersion());
            existingBox.setInstallationDate(updatedBox.getInstallationDate());
            existingBox.setLightingType(updatedBox.getLightingType());
            existingBox.setModele(updatedBox.getModele());
            existingBox.setSimNumber(updatedBox.getSimNumber());

            Box saved = repository.save(existingBox);
            eventProducer.sendBoxEvent(saved);
            return saved;
        }).orElseThrow(() -> new RuntimeException("Box not found with id: " + id));
    }

    public void deleteBox(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Box not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
