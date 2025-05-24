package tn.esprit.projetkafka.command.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.command.repository.OnduleurRepository;
import tn.esprit.projetkafka.event.producer.OnduleurEventProducer;

@Service
public class OnduleurService {
    private final OnduleurRepository repository;
    private final OnduleurEventProducer eventProducer;

    public OnduleurService(OnduleurRepository repository, OnduleurEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Onduleur createOnduleur(Onduleur onduleur) {
        Onduleur savedOnduleur = repository.save(onduleur);
        eventProducer.sendOnduleurEvent(savedOnduleur);
        return savedOnduleur;
    }

    public Onduleur updateOnduleur(Long id, Onduleur updatedOnduleur) {
        return repository.findById(id).map(existingOnduleur -> {
            // Mise à jour des champs
            existingOnduleur.setCode(updatedOnduleur.getCode());
            existingOnduleur.setDescription(updatedOnduleur.getDescription());
            existingOnduleur.setSite(updatedOnduleur.getSite());
            existingOnduleur.setLocale(updatedOnduleur.getLocale());
            existingOnduleur.setBox(updatedOnduleur.getBox());
            existingOnduleur.setIndex(updatedOnduleur.getIndex());
            existingOnduleur.setDateCommunication(updatedOnduleur.getDateCommunication());
            existingOnduleur.setHeure(updatedOnduleur.getHeure());
            existingOnduleur.setConsommationKwh(updatedOnduleur.getConsommationKwh());
            existingOnduleur.setProductionKwh(updatedOnduleur.getProductionKwh());
            existingOnduleur.setBloque(updatedOnduleur.getBloque());
            Onduleur saved = repository.save(existingOnduleur);
            eventProducer.sendOnduleurEvent(saved);
            return saved;
        }).orElseThrow(() -> new RuntimeException("Onduleur not found with id: " + id));
    }

    public void deleteOnduleur(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Onduleur not found with id: " + id);
        }

        repository.deleteById(id);

        // Créer un objet Onduleur minimal pour l'événement de suppression
        Onduleur deletedOnduleur = new Onduleur();
        deletedOnduleur.setId(id);
        // Ne pas définir eventType, car il n'existe pas dans Onduleur
        eventProducer.sendOnduleurEvent(deletedOnduleur);
    }
}