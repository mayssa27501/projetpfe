package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Site;
import tn.esprit.projetkafka.query.entity.SiteView;
import tn.esprit.projetkafka.query.repository.SiteViewRepository;

@Component
public class SiteEventConsumer {
    private final SiteViewRepository queryRepository;

    @Autowired
    public SiteEventConsumer(SiteViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "site-events", groupId = "site-group")
    public void consumeSiteEvent(Site site) {
        SiteView siteView = new SiteView();
        siteView.setId(site.getId());
        siteView.setDescription(site.getDescription());
        siteView.setAddress(site.getAddress());
        siteView.setTimeZone(site.getTimeZone());
        siteView.setPositionGps(site.getPositionGps());
        siteView.setIsBlocked(site.getIsBlocked());
        siteView.setIsPrincipal(site.getIsPrincipal());
        queryRepository.save(siteView);
    }
}