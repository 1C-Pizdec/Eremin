package com.evseenkovia.microservices.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String producer;
    private Integer price;
    private String country;

    // Дополнительные поля для микросервиса
    private Integer stockQuantity;  // Количество на складе
    private String category;        // Категория
    private Boolean available;      // Доступен ли для заказа

    public Product(){}

    public Product(String name, String producer, Integer price, String category, String country, Integer stockQuantity) {
        this.name = name;
        this.producer = producer;
        this.price = price;
        this.country = country;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.available = true;
    }
}
