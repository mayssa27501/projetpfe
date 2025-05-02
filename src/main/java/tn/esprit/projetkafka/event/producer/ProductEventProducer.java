package tn.esprit.projetkafka.event.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Product;

@Component
public class ProductEventProducer {
    private final KafkaTemplate<String, Product> kafkaTemplate;

    @Autowired
    public ProductEventProducer(KafkaTemplate<String, Product> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendProductEvent(Product product) {
        kafkaTemplate.send("product-events", product);
    }
}

