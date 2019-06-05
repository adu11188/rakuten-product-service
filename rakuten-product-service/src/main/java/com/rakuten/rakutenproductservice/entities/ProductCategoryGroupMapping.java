package com.rakuten.rakutenproductservice.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the product_category_group_mapping database table.
 * 
 */
@Entity
@Table(name = "product_category_group_mapping", schema = "product_categories_schema")
//@NamedQuery(name="ProductCategoryGroupMapping.findAll", query="SELECT p FROM ProductCategoryGroupMapping p")
public class ProductCategoryGroupMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//bi-directional many-to-one association to CategoryGroup
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="category_group_id")
	private CategoryGroup categoryGroup;

	//bi-directional many-to-one association to Product
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="product_id")
	private Product product;

	public ProductCategoryGroupMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CategoryGroup getCategoryGroup() {
		return this.categoryGroup;
	}

	public void setCategoryGroup(CategoryGroup categoryGroup) {
		this.categoryGroup = categoryGroup;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}