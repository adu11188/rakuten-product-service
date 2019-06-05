package com.rakuten.rakutenproductservice.product.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.rakutenproductservice.entities.Product;
import com.rakuten.rakutenproductservice.exception.ProductDaoException;
import com.rakuten.rakutenproductservice.exception.ProductEntityNotFoundException;
import com.rakuten.rakutenproductservice.repo.ProductRepository;
/**
 * Product DAO implementation class
 * 
 * @author adarshsumma
 *
 */
@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	ProductRepository productRepo;
		
	/**
	 * Fetch product for a given product Id
	 * 
	 */
	@Override
	public Product getProduct(long id) throws ProductEntityNotFoundException,ProductDaoException {
		Product product = null;
		try {
			product = productRepo.findById(id).orElseThrow(()->new ProductEntityNotFoundException("Product does not exist"));
			return product;
		}catch(IllegalArgumentException  e) {
			throw new ProductDaoException("Product id is null",e);
		}
		
	}

	/**
	 * Fetch all products
	 * 
	 */
	@Override
	public List<Product> getProducts() {
		return productRepo.findAll();
	}

	/**
	 * Create new product
	 * 
	 */
	@Override
	public Product createProduct(Product product) {
	    Product savedProduct = productRepo.save(product);
		return savedProduct;

	}

	/**
	 * Update product for a given product id
	 */
	@Override
	public Product updateProduct(Product product) throws ProductEntityNotFoundException,ProductDaoException {
		try {
			if(productRepo.findById(product.getProductId()). isPresent()) {
				product = productRepo.save(product);
				return product;
			}else {
				throw new ProductEntityNotFoundException("Product does not exist");
			}
		}catch(IllegalArgumentException  e) {
			throw new ProductDaoException("Product id is null",e);
		}
		
	}

	/**
	 * Delete product for a given product id
	 * 
	 */
	@Override
	public void deleteProduct(long id) throws ProductEntityNotFoundException,ProductDaoException {
		try {
			if(productRepo.findById(id). isPresent()) {
				productRepo.deleteById(id);
			}else {
				throw new ProductEntityNotFoundException("Product does not exist");
			}
			
		}catch(IllegalArgumentException  e) {
			throw new ProductDaoException("Product id is null",e);
		}
		
	}

}
