package com.rakuten.rakutenproductservice.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakuten.rakutenproductservice.entities.Category;
/**
 * Category repository
 * 
 * @author adarshsumma
 *
 */
public interface CategoryRepository extends JpaRepository<Category,Long>
{
	List<Category> findByCategoryIdIn(List<Long> ids);
	List<Category> findByCategoryNameIn(Set<String> catgegories);
	Category findByCategoryName(String categoryName);
	
}
