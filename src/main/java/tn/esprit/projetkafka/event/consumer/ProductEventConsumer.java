package tn.esprit.projetkafka.event.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.projetkafka.command.entity.Product;
import tn.esprit.projetkafka.query.entity.ProductView;
import tn.esprit.projetkafka.query.repository.ProductViewRepository;

@Component
public class ProductEventConsumer {
    private final ProductViewRepository queryRepository;

    @Autowired
    public ProductEventConsumer(ProductViewRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @KafkaListener(topics = "product-events", groupId = "product-group")
    public void consumeProductEvent(Product product) {
        ProductView productView = new ProductView();
        productView.setId(product.getId());
        productView.setName(product.getName());
        productView.setPrice(product.getPrice());
        queryRepository.save(productView);
    }
}
