package com.rakuten.rakutenproductservice.category.dao.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.rakutenproductservice.entities.Category;
import com.rakuten.rakutenproductservice.entities.CategoryGroup;
import com.rakuten.rakutenproductservice.exception.CategoryDaoException;
import com.rakuten.rakutenproductservice.exception.CategoryEntityNotFoundException;
import com.rakuten.rakutenproductservice.repo.CategoryGroupRepository;
import com.rakuten.rakutenproductservice.repo.CategoryRepository;

/**
 * Category and category group DAO implementation class
 * 
 * @author adarshsumma
 *
 */
@Repository("CategoryDaoImpl")
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	CategoryGroupRepository categoryGroupRepo;

	@Override
	public Category getCategory(long id) throws CategoryEntityNotFoundException {
		Category category = null;
		category = categoryRepo.findById(id)
				.orElseThrow(() -> new CategoryEntityNotFoundException("Category does not exist"));
		return category;

	}

	@Override
	public List<Category> getCategories(List<Long> ids) {
		return categoryRepo.findAllById(ids);
	}

	@Override
	public Category createCategory(Category category) {
		category = categoryRepo.save(category);
		return category;
	}

	@Override
	public Category updateCategory(Category category) throws CategoryEntityNotFoundException, CategoryDaoException {

		try {
			if (categoryRepo.findById(category.getCategoryId()).isPresent()) {
				category = categoryRepo.save(category);
				return category;
			} else {
				throw new CategoryEntityNotFoundException("Currency does not exist");
			}
		} catch (IllegalArgumentException e) {
			throw new CategoryDaoException("Currency id is null", e);
		}

	}

	@Override
	public void deleteCategory(long id) throws CategoryEntityNotFoundException, CategoryDaoException {

		try {
			if (categoryRepo.findById(id).isPresent()) {
				categoryRepo.deleteById(id);
			} else {
				throw new CategoryEntityNotFoundException("Category does not exist");
			}

		} catch (IllegalArgumentException e) {
			throw new CategoryDaoException("Category id is null", e);
		}

	}

	@Override
	public List<Category> getCategories() {
		return categoryRepo.findAll();
	}

	@Override
	public List<Category> findByCategoryNameIn(Set<String> catgrs) {
		List<Category> categories = categoryRepo.findByCategoryNameIn(catgrs);
		return categories;
	}

	@Override
	public List<Category> createCategories(List<Category> categories) {
		categories = categoryRepo.saveAll(categories);
		return categories;
	}

	@Override
	public CategoryGroup createCategoryGroup(CategoryGroup categoryGroup) {
		categoryGroup = categoryGroupRepo.save(categoryGroup);
		return categoryGroup;
	}

	@Override
	public List<CategoryGroup> createCategoryGroups(List<CategoryGroup> categoryGroups) {
		categoryGroups = categoryGroupRepo.saveAll(categoryGroups);
		return categoryGroups;
	}
}
