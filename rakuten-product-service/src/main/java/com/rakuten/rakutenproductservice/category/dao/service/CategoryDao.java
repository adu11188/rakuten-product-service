package com.rakuten.rakutenproductservice.category.dao.service;

import java.util.List;
import java.util.Set;

import com.rakuten.rakutenproductservice.entities.Category;
import com.rakuten.rakutenproductservice.entities.CategoryGroup;
import com.rakuten.rakutenproductservice.exception.CategoryDaoException;
import com.rakuten.rakutenproductservice.exception.CategoryEntityNotFoundException;
/**
 * Category and category group Dao interface
 * 
 * Wrapper interface for JPA repository
 * 
 * @author adarshsumma
 *
 */
public interface CategoryDao {

	Category getCategory(long id) throws CategoryEntityNotFoundException;
	List<Category> getCategories();
	Category createCategory(Category category);
	List<Category> createCategories(List<Category> categories);
	Category updateCategory(Category category) throws CategoryEntityNotFoundException,CategoryDaoException;
	void  deleteCategory(long id)throws  CategoryDaoException;
	List<Category> getCategories(List<Long> ids);
	CategoryGroup createCategoryGroup(CategoryGroup categoryGroup);
	List<CategoryGroup> createCategoryGroups(List<CategoryGroup> categoryGroups);
	List<Category> findByCategoryNameIn(Set<String> catgrs);
	
}
