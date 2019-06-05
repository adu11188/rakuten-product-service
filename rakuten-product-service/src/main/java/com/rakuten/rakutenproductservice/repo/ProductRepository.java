package com.rakuten.rakutenproductservice.repo;

import com.rakuten.rakutenproductservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Product repository
 * 
 */
public interface ProductRepository extends JpaRepository<Product,Long>{

}
