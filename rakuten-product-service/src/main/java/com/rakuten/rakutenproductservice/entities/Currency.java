package com.rakuten.rakutenproductservice.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the currency database table.
 * 
 */
@Entity
//@NamedQuery(name="Currency.findAll", query="SELECT c FROM Currency c")
@Table(name = "currency", schema = "product_categories_schema")
public class Currency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="currency_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long currencyId;

	@Column(name="currency_code")
	private String currencyCode;

	@Column(name="currency_name")
	private String currencyName;

	@Column(name="currency_symbol")
	private String currencySymbol;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="currency", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Product> products;

	public Currency() {
	}

	public Long getCurrencyId() {
		return this.currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getCurrencyName() {
		return this.currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public String getCurrencySymbol() {
		return this.currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setCurrency(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setCurrency(null);

		return product;
	}

}