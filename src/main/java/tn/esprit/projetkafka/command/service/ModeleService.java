package tn.esprit.projetkafka.command.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Modele;
import tn.esprit.projetkafka.command.repository.ModeleRepository;
import tn.esprit.projetkafka.event.producer.ModeleEventProducer;

@Service
public class ModeleService {
    private final ModeleRepository repository;
    private final ModeleEventProducer eventProducer;

    public ModeleService(ModeleRepository repository, ModeleEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Modele createModele(Modele modele) {
        Modele savedModele = repository.save(modele);
        eventProducer.sendModeleEvent(savedModele);
        return savedModele;
    }

    public Modele updateModele(Long id, Modele updatedModele) {
        return repository.findById(id).map(existingModele -> {
            existingModele.setName(updatedModele.getName());
            existingModele.setDescription(updatedModele.getDescription());
            existingModele.setAttributes(updatedModele.getAttributes());
            Modele saved = repository.save(existingModele);
            eventProducer.sendModeleEvent(saved);
            return saved;
        }).orElseThrow(() -> new RuntimeException("Modele not found with id: " + id));
    }

    public void deleteModele(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Modele not found with id: " + id);
        }
        repository.deleteById(id);
    }
}