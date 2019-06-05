package com.rakuten.rakutenproductservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
//@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
@Table(name = "product", schema = "product_categories_schema")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(name="available")
	private Boolean available;

	@Column(name="brand")
	private String brand;

	@Column(name="color")
	private String color;

	@Column(name="description")
	private String description;

	@Column(name="gender")
	private String gender;

	@Column(name="product_name")
	private String productName;

	@Column(name="sale_price")
	private BigDecimal salePrice;

	@OneToOne
	@JoinColumn(name="currency_id")
	private Currency currency;

	//bi-directional many-to-one association to ProductCategoryGroupMapping
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="product_id")
	private List<ProductCategoryGroupMapping> productCategoryGroupMappings;

	public Product() {
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return this.color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Currency getCurrency() {
		return this.currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<ProductCategoryGroupMapping> getProductCategoryGroupMappings() {
		return this.productCategoryGroupMappings;
	}

	public void setProductCategoryGroupMappings(List<ProductCategoryGroupMapping> productCategoryGroupMappings) {
		this.productCategoryGroupMappings = productCategoryGroupMappings;
	}

	public ProductCategoryGroupMapping addProductCategoryGroupMapping(ProductCategoryGroupMapping productCategoryGroupMapping) {
		getProductCategoryGroupMappings().add(productCategoryGroupMapping);
		productCategoryGroupMapping.setProduct(this);

		return productCategoryGroupMapping;
	}

	public ProductCategoryGroupMapping removeProductCategoryGroupMapping(ProductCategoryGroupMapping productCategoryGroupMapping) {
		getProductCategoryGroupMappings().remove(productCategoryGroupMapping);
		productCategoryGroupMapping.setProduct(null);

		return productCategoryGroupMapping;
	}

}