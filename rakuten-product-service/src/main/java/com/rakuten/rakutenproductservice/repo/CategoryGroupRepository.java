package com.rakuten.rakutenproductservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakuten.rakutenproductservice.entities.CategoryGroup;
/**
 * Category repository
 * 
 * @author adarshsumma
 *
 */
public interface CategoryGroupRepository  extends JpaRepository<CategoryGroup,Long>{

}
