package com.evseenkovia.microservices.repository;

import com.evseenkovia.microservices.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProducer(String producer);

    List<Product> findByCategory(String category);

    List<Product> findByCountry(String country);

    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

    List<Product> findByAvailableTrue();
}
