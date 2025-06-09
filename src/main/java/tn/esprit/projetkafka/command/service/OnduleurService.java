package tn.esprit.projetkafka.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Box;
import tn.esprit.projetkafka.command.entity.Onduleur;
import tn.esprit.projetkafka.command.repository.BoxRepository;
import tn.esprit.projetkafka.command.repository.OnduleurRepository;

import java.util.Optional;

@Service
public class OnduleurService {

    private final OnduleurRepository onduleurRepository;
    private final BoxRepository boxRepository;

    @Autowired
    public OnduleurService(OnduleurRepository onduleurRepository, BoxRepository boxRepository) {
        this.onduleurRepository = onduleurRepository;
        this.boxRepository = boxRepository;
    }

    public Onduleur createOnduleur(Onduleur onduleur) {
        if (onduleur.getBox() != null && onduleur.getBox().getId() != null) {
            Optional<Box> optionalBox = boxRepository.findById(onduleur.getBox().getId());
            if (optionalBox.isPresent()) {
                onduleur.setBox(optionalBox.get());
            } else {
                throw new RuntimeException("Box with id " + onduleur.getBox().getId() + " not found");
            }
        } else {
            onduleur.setBox(null); // Pas de box associée
        }
        return onduleurRepository.save(onduleur);
    }

    public Onduleur updateOnduleur(Long id, Onduleur onduleur) {
        Onduleur existingOnduleur = onduleurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Onduleur with id " + id + " not found"));

        existingOnduleur.setCode(onduleur.getCode());

        if (onduleur.getBox() != null && onduleur.getBox().getId() != null) {
            Box box = boxRepository.findById(onduleur.getBox().getId())
                    .orElseThrow(() -> new RuntimeException("Box with id " + onduleur.getBox().getId() + " not found"));
            existingOnduleur.setBox(box);
        } else {
            existingOnduleur.setBox(null);
        }

        return onduleurRepository.save(existingOnduleur);
    }

    public void deleteOnduleur(Long id) {
        if (!onduleurRepository.existsById(id)) {
            throw new RuntimeException("Onduleur with id " + id + " not found");
        }
        onduleurRepository.deleteById(id);
    }
}
