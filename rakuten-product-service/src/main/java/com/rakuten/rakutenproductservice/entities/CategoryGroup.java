package com.rakuten.rakutenproductservice.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the category_group database table.
 * 
 */
@Entity
@Table(name = "category_group", schema = "product_categories_schema")
//@NamedQuery(name="CategoryGroup.findAll", query="SELECT c FROM CategoryGroup c")
public class CategoryGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_group_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryGroupId;

	@Column(name="category_group_name")
	private String categoryGroupName;

	//bi-directional many-to-one association to CategoryGroupMapping
	@OneToMany(mappedBy="categoryGroup", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<CategoryGroupMapping> categoryGroupMappings;

	//bi-directional many-to-one association to ProductCategoryGroupMapping
	@OneToMany(mappedBy="categoryGroup",fetch=FetchType.LAZY)
	private List<ProductCategoryGroupMapping> productCategoryGroupMappings;

	public CategoryGroup() {
	}

	public Long getCategoryGroupId() {
		return this.categoryGroupId;
	}

	public void setCategoryGroupId(Long categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
	}

	public String getCategoryGroupName() {
		return this.categoryGroupName;
	}

	public void setCategoryGroupName(String categoryGroupName) {
		this.categoryGroupName = categoryGroupName;
	}

	public List<CategoryGroupMapping> getCategoryGroupMappings() {
		return this.categoryGroupMappings;
	}

	public void setCategoryGroupMappings(List<CategoryGroupMapping> categoryGroupMappings) {
		this.categoryGroupMappings = categoryGroupMappings;
	}

	public CategoryGroupMapping addCategoryGroupMapping(CategoryGroupMapping categoryGroupMapping) {
		getCategoryGroupMappings().add(categoryGroupMapping);
		categoryGroupMapping.setCategoryGroup(this);

		return categoryGroupMapping;
	}

	public CategoryGroupMapping removeCategoryGroupMapping(CategoryGroupMapping categoryGroupMapping) {
		getCategoryGroupMappings().remove(categoryGroupMapping);
		categoryGroupMapping.setCategoryGroup(null);

		return categoryGroupMapping;
	}

	public List<ProductCategoryGroupMapping> getProductCategoryGroupMappings() {
		return this.productCategoryGroupMappings;
	}

	public void setProductCategoryGroupMappings(List<ProductCategoryGroupMapping> productCategoryGroupMappings) {
		this.productCategoryGroupMappings = productCategoryGroupMappings;
	}

	public ProductCategoryGroupMapping addProductCategoryGroupMapping(ProductCategoryGroupMapping productCategoryGroupMapping) {
		getProductCategoryGroupMappings().add(productCategoryGroupMapping);
		productCategoryGroupMapping.setCategoryGroup(this);

		return productCategoryGroupMapping;
	}

	public ProductCategoryGroupMapping removeProductCategoryGroupMapping(ProductCategoryGroupMapping productCategoryGroupMapping) {
		getProductCategoryGroupMappings().remove(productCategoryGroupMapping);
		productCategoryGroupMapping.setCategoryGroup(null);

		return productCategoryGroupMapping;
	}

}