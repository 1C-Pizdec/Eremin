package com.evseenkovia.microservices;

import com.evseenkovia.microservices.model.Product;
import com.evseenkovia.microservices.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class Lab3MicroservicesApplication implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args){
        SpringApplication.run(Lab3MicroservicesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(
                new Product("Samsung A55", "Samsung Ltd", 30000, "Smartphone", "China", 350));
        // Смартфоны
        productRepository.save(new Product("iPhone 15 Pro", "Apple", 999, "Smartphone", "USA", 150));
        productRepository.save(new Product("Samsung Galaxy S24", "Samsung", 899, "Smartphone", "South Korea", 200));
        productRepository.save(new Product("Xiaomi 14 Pro", "Xiaomi", 699, "Smartphone", "China", 300));

        // Ноутбуки
        productRepository.save(new Product("MacBook Pro 16", "Apple", 2499, "Laptop", "USA", 80));
        productRepository.save(new Product("Lenovo ThinkPad X1", "Lenovo", 1799, "Laptop", "China", 100));
        productRepository.save(new Product("Dell XPS 15", "Dell", 1599, "Laptop", "USA", 90));

        // Наушники
        productRepository.save(new Product("Sony WH-1000XM5", "Sony", 349, "Headphones", "Japan", 250));
        productRepository.save(new Product("AirPods Pro 2", "Apple", 249, "Headphones", "USA", 400));

        // Планшеты
        productRepository.save(new Product("iPad Pro 12.9", "Apple", 1099, "Tablet", "USA", 120));
        productRepository.save(new Product("Samsung Galaxy Tab S9", "Samsung", 849, "Tablet", "South Korea", 150));
    }
}
