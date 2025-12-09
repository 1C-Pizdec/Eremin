package ru.bmstu.rvvs;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	/*
	 * @Query("SELECT * FROM Product WHERE name LIKE :x") public Page<Product>
	 * findProduct(@Param("x") String keyWord, Pageable pageable);
	 */
}
