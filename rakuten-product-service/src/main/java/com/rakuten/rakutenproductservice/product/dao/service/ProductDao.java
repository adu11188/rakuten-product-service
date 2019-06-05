package com.rakuten.rakutenproductservice.product.dao.service;

import java.util.List;

import com.rakuten.rakutenproductservice.entities.Product;
import com.rakuten.rakutenproductservice.exception.ProductDaoException;
import com.rakuten.rakutenproductservice.exception.ProductEntityNotFoundException;
/**
 * Product DAO wrapper for Repository
 * 
 * @author adarshsumma
 *
 */
public interface ProductDao {

	Product getProduct(long id) throws ProductEntityNotFoundException,ProductDaoException ;
	List<Product> getProducts() ;
	Product createProduct(Product product) ;
	Product updateProduct(Product product) throws ProductEntityNotFoundException,ProductDaoException ;
	void  deleteProduct(long id) throws ProductEntityNotFoundException,ProductDaoException ;
	
	
}
