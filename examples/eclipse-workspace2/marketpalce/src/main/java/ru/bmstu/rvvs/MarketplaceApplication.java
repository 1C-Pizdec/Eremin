package ru.bmstu.rvvs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketplaceApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepositiry;
	
	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		productRepositiry.save(new Product("Холодильник", "Samsung", 100, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Минск", 200, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 300, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 400, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Минск", 500, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 600, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 700, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Минск", 800, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 900, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 1000, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Минск", 1100, "Беларусь"));
		productRepositiry.save(new Product("Холодильник", "Samsung", 1200, "Беларусь"));
	}
	
	
	

}
