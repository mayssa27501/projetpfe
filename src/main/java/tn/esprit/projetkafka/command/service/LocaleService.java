package tn.esprit.projetkafka.command.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Locale;
import tn.esprit.projetkafka.command.repository.LocaleRepository;
import tn.esprit.projetkafka.event.producer.LocaleEventProducer;

@Service
public class LocaleService {
    private final LocaleRepository repository;
    private final LocaleEventProducer eventProducer;

    public LocaleService(LocaleRepository repository, LocaleEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Locale createLocale(Locale locale) {
        Locale savedLocale = repository.save(locale);
        eventProducer.sendLocaleEvent(savedLocale);
        return savedLocale;
    }

    public Locale updateLocale(Long id, Locale updatedLocale) {
        return repository.findById(id).map(existingLocale -> {
            existingLocale.setCode(updatedLocale.getCode());
            existingLocale.setName(updatedLocale.getName());
            existingLocale.setDescription(updatedLocale.getDescription());
            existingLocale.setBlocked(updatedLocale.getBlocked());
            existingLocale.setSite(updatedLocale.getSite()); // Added site update
            Locale saved = repository.save(existingLocale);
            eventProducer.sendLocaleEvent(saved);
            return saved;
        }).orElseThrow(() -> new RuntimeException("Locale not found with id: " + id));
    }

    public void deleteLocale(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Locale not found with id: " + id);
        }
        repository.deleteById(id);
        Locale deletedLocale = new Locale();
        deletedLocale.setId(id);
        eventProducer.sendLocaleEvent(deletedLocale);
    }
}