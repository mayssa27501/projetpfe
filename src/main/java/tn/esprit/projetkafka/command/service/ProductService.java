package tn.esprit.projetkafka.command.service;

import org.springframework.stereotype.Service;
import tn.esprit.projetkafka.command.entity.Product;
import tn.esprit.projetkafka.command.repository.ProductRepository;
import tn.esprit.projetkafka.event.producer.ProductEventProducer;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductEventProducer eventProducer;

    public ProductService(ProductRepository repository, ProductEventProducer eventProducer) {
        this.repository = repository;
        this.eventProducer = eventProducer;
    }

    public Product createProduct(Product product) {
        Product savedProduct = repository.save(product);
        eventProducer.sendProductEvent(savedProduct);
        return savedProduct;
    }
    public Product updateProduct(Long id, Product updatedProduct) {
        return repository.findById(id).map(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            Product saved = repository.save(existingProduct);
            eventProducer.sendProductEvent(saved); // Optional: trigger event again
            return saved;
        }).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
    public void deleteProduct(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        repository.deleteById(id);
    }


}
