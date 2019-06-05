package com.rakuten.rakutenproductservice.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category_group_mapping database table.
 * 
 */
@Entity
@Table(name = "category_group_mapping", schema = "product_categories_schema")
//@NamedQuery(name="CategoryGroupMapping.findAll", query="SELECT c FROM CategoryGroupMapping c")
public class CategoryGroupMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CategoryGroupMappingPK id;

	//bi-directional many-to-one association to Category
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name="category_id", insertable=false, updatable=false)
	private Category category;

	//bi-directional many-to-one association to CategoryGroup
	@ManyToOne(cascade = {CascadeType.REFRESH})
	@JoinColumn(name="category_group_id", insertable=false, updatable=false)
	private CategoryGroup categoryGroup;

	public CategoryGroupMapping() {
	}

	public CategoryGroupMappingPK getId() {
		return this.id;
	}

	public void setId(CategoryGroupMappingPK id) {
		this.id = id;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public CategoryGroup getCategoryGroup() {
		return this.categoryGroup;
	}

	public void setCategoryGroup(CategoryGroup categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

}