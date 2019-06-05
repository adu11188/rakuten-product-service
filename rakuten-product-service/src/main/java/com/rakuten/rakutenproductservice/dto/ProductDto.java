package com.rakuten.rakutenproductservice.dto;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Product Data Transfer object
 * 
 * @author adarshsumma
 *
 */
public class ProductDto {
	
	@NotNull(message = "Currency can't be empty!")
	private Currency currency;

	@NotBlank(message = "productName can't be empty!")
	private String productName;
	private String description;
	private String brand;
	private String color;
	private Boolean available;
	private String gender;
	@NotNull(message = "salePrice can't be empty!")
	private BigDecimal salePrice;
	private List<List<String>> categoryFullPaths;

	public ProductDto() {

	}

	public ProductDto(Currency currency, String productName, String description, String brand, String color,
			Boolean available, String gender, List<List<String>> categoryFullPaths, BigDecimal salePrice) {
		super();
		this.currency = currency;
		this.productName = productName;
		this.description = description;
		this.brand = brand;
		this.color = color;
		this.available = available;
		this.gender = gender;
		this.setCategoryFullPaths(categoryFullPaths);
		this.salePrice = salePrice;
	}

	public ProductDto(Long productId, Currency currency, String productName, String description, String brand,
			String color, Boolean available, String gender, BigDecimal salePrice) {
		super();
		this.currency = currency;
		this.productName = productName;
		this.description = description;
		this.brand = brand;
		this.color = color;
		this.available = available;
		this.gender = gender;
		this.salePrice = salePrice;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public List<List<String>> getCategoryFullPaths() {
		return categoryFullPaths;
	}

	public void setCategoryFullPaths(List<List<String>> categoryFullPaths) {
		this.categoryFullPaths = categoryFullPaths;
	}

	/**
	 * Currency Data Transfer object for Product
	 * 
	 * @author adarshsumma
	 *
	 */
	public static class Currency {
		private String curencyName;
		@NotBlank(message = "currencyCode can't be empty!")
		private String currencyCode;
		private String currencySymbol;

		public Currency() {

		}

		public Currency(String curencyName, String currencyCode, String currencySymbol) {
			super();
			this.curencyName = curencyName;
			this.currencyCode = currencyCode;
			this.currencySymbol = currencySymbol;
		}

		public String getCurencyName() {
			return curencyName;
		}

		public void setCurencyName(String curencyName) {
			this.curencyName = curencyName;
		}

		public String getCurrencyCode() {
			return currencyCode;
		}

		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}

		public String getCurrencySymbol() {
			return currencySymbol;
		}

		public void setCurrencySymbol(String currencySymbol) {
			this.currencySymbol = currencySymbol;
		}
	}

	/**
	 * Builder class for Product DTO
	 * 
	 * @author adarshsumma
	 *
	 */
	public static class ProductDtoBuilder {
		private Currency currency;
		private String productName;
		private String description;
		private String brand;
		private String color;
		private Boolean available;
		private String gender;
		private BigDecimal salePrice;
		private String curencyName;
		private String currencyCode;
		private String currencySymbol;
		private List<List<String>> categoryFullPaths;

		public ProductDtoBuilder setCategoryFullPaths(List<List<String>> categoryFullPaths) {
			this.categoryFullPaths = categoryFullPaths;
			return this;
		}

		public ProductDtoBuilder setCurencyName(String curencyName) {
			this.curencyName = curencyName;
			return this;
		}

		public ProductDtoBuilder setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
			return this;
		}

		public ProductDtoBuilder setCurrencySymbol(String currencySymbol) {
			this.currencySymbol = currencySymbol;
			return this;
		}

		public ProductDtoBuilder setCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public ProductDtoBuilder setProductName(String productName) {
			this.productName = productName;
			return this;
		}

		public ProductDtoBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		public ProductDtoBuilder setBrand(String brand) {
			this.brand = brand;
			return this;
		}

		public ProductDtoBuilder setColor(String color) {
			this.color = color;
			return this;
		}

		public Boolean isAvailable() {
			return available;
		}

		public ProductDtoBuilder setAvailable(Boolean available) {
			this.available = available;
			return this;
		}

		public ProductDtoBuilder setGender(String gender) {
			this.gender = gender;
			return this;
		}

		public ProductDtoBuilder setSalePrice(BigDecimal salePrice) {
			this.salePrice = salePrice;
			return this;
		}

		public ProductDto build() {
			return new ProductDto(currency != null ? currency : new Currency(curencyName, currencyCode, currencySymbol),
					productName, description, brand, color, available, gender, categoryFullPaths, salePrice);
		}

	}
}
