package com.rakuten.rakutenproductservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
//@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
@Table(name = "category", schema = "product_categories_schema")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(name="category_name")
	private String categoryName;

	//bi-directional many-to-one association to CategoryGroupMapping
	@OneToMany(mappedBy="category", cascade = {CascadeType.ALL})
	private List<CategoryGroupMapping> categoryGroupMappings;

	public Category() {
	}

	public Long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<CategoryGroupMapping> getCategoryGroupMappings() {
		return this.categoryGroupMappings;
	}

	public void setCategoryGroupMappings(List<CategoryGroupMapping> categoryGroupMappings) {
		this.categoryGroupMappings = categoryGroupMappings;
	}

	public CategoryGroupMapping addCategoryGroupMapping(CategoryGroupMapping categoryGroupMapping) {
		getCategoryGroupMappings().add(categoryGroupMapping);
		categoryGroupMapping.setCategory(this);

		return categoryGroupMapping;
	}

	public CategoryGroupMapping removeCategoryGroupMapping(CategoryGroupMapping categoryGroupMapping) {
		getCategoryGroupMappings().remove(categoryGroupMapping);
		categoryGroupMapping.setCategory(null);

		return categoryGroupMapping;
	}

	

}