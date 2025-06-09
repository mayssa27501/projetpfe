package tn.esprit.projetkafka.command.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Site;
import tn.esprit.projetkafka.command.repository.SiteRepository;
import tn.esprit.projetkafka.event.producer.SiteEventProducer;

@Service
public class SiteService {
    private final SiteRepository repository;
    private final SiteEventProducer eventProducer;

    public SiteService(SiteRepository repository, SiteEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Site createSite(Site site) {
        Site savedSite = repository.save(site);
        eventProducer.sendSiteEvent(savedSite);
        return savedSite;
    }

    public Site updateSite(Long id, Site updatedSite) {
        return repository.findById(id).map(existingSite -> {
            existingSite.setDescription(updatedSite.getDescription());
            existingSite.setAddress(updatedSite.getAddress());
            existingSite.setTimeZone(updatedSite.getTimeZone());
            existingSite.setPositionGps(updatedSite.getPositionGps());
            existingSite.setIsBlocked(updatedSite.getIsBlocked());
            existingSite.setIsPrincipal(updatedSite.getIsPrincipal());
//            existingSite.setSociety(updatedSite.getSociety());
            Site saved = repository.save(existingSite);
            eventProducer.sendSiteEvent(saved);
            return saved;
        }).orElseThrow(() -> new RuntimeException("Site not found with id: " + id));
    }

    public void deleteSite(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Site not found with id: " + id);
        }
        repository.deleteById(id);
    }
}