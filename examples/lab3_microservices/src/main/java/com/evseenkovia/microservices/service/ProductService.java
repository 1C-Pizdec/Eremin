package com.evseenkovia.microservices.service;

import com.evseenkovia.microservices.model.Product;
import com.evseenkovia.microservices.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(Product product) {
        if (product.getStockQuantity() == null) {
            product.setStockQuantity(0);
        }
        if (product.getAvailable() == null) {
            product.setAvailable(product.getStockQuantity() > 0);
        }
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = getProductById(id);

        product.setName(productDetails.getName());
        product.setProducer(productDetails.getProducer());
        product.setPrice(productDetails.getPrice());
        product.setCountry(productDetails.getCountry());
        product.setCategory(productDetails.getCategory());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setAvailable(productDetails.getAvailable());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByProducer(String producer) {
        return productRepository.findByProducer(producer);
    }

    public List<Product> getAvailableProducts() {
        return productRepository.findByAvailableTrue();
    }

    @Transactional
    public Product updateStock(Long id, Integer quantity) {
        Product product = getProductById(id);

        if (quantity < 0) {
            throw new RuntimeException("Stock quantity cannot be negative");
        }

        product.setStockQuantity(quantity);
        product.setAvailable(quantity > 0);

        log.info("Updated stock for product {}: new quantity = {}",
                product.getName(), quantity);

        return productRepository.save(product);
    }

    @Transactional
    public Product updatePrice(Long id, Integer newPrice) {
        Product product = getProductById(id);

        if (newPrice <= 0) {
            throw new RuntimeException("Price must be positive");
        }

        product.setPrice(newPrice);
        log.info("Updated price for product {}: {} -> {}",
                product.getName(), product.getPrice(), newPrice);

        return productRepository.save(product);
    }

    @Transactional
    public Product reserveProduct(Long id, Integer quantity) {
        Product product = getProductById(id);

        if (!product.getAvailable()) {
            throw new RuntimeException("Product is not available");
        }

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock. Available: " +
                    product.getStockQuantity() + ", requested: " + quantity);
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        product.setAvailable(product.getStockQuantity() > 0);

        log.info("Reserved {} units of product {}", quantity, product.getName());

        return productRepository.save(product);
    }
}