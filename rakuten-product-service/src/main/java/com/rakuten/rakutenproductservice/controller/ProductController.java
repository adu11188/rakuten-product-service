package com.rakuten.rakutenproductservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.exception.CurrencyDaoException;
import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyServiceException;
import com.rakuten.rakutenproductservice.exception.ProductNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductServiceException;
/**
 * Product controller 
 * 
 * @author adarshsumma
 *
 */
public interface ProductController {
/**
 * Get product details for a given product Id
 * @param id
 * @param currencyCode
 * @return
 * @throws ProductServiceException
 * @throws ProductNotFoundException
 * @throws CurrencyDaoException
 * @throws CurrencyNotFoundException 
 */
	ResponseEntity<ProductDto> getProduct(long id, String currencyCode)
			throws ProductServiceException, ProductNotFoundException, CurrencyDaoException, CurrencyNotFoundException;
/**
 * Get categories for a given product Id
 * 
 * @param id
 * @return
 * @throws ProductServiceException
 * @throws ProductNotFoundException
 */
	ResponseEntity<List<List<String>>> getProductCategories(long id)
			throws ProductServiceException, ProductNotFoundException;
/**
 * Fetch all products
 * Optional: add target currency code if sale price to be got a particular currency
 * @param currencyCode
 * @return
 * @throws ProductServiceException
 * @throws CurrencyDaoException
 * @throws CurrencyServiceException
 * @throws CurrencyNotFoundException 
 */
	ResponseEntity<List<ProductDto>> getProducts(String currencyCode)
			throws ProductServiceException, CurrencyDaoException, CurrencyServiceException, CurrencyNotFoundException;
/**
 * Add new product
 * 
 * @param productDto
 * @return
 * @throws ProductServiceException
 * @throws CurrencyNotFoundException
 */
	ResponseEntity<ProductDto> createProduct(ProductDto productDto)
			throws ProductServiceException, CurrencyNotFoundException;
/**
 * Update a product for a given Product Id
 * @param id
 * @param productDto
 * @return
 * @throws ProductServiceException
 * @throws CurrencyNotFoundException
 * @throws ProductNotFoundException
 */
	ResponseEntity<ProductDto> updateProduct(long id, ProductDto productDto)
			throws ProductServiceException, CurrencyNotFoundException, ProductNotFoundException;
/**
 * Delete a given product based on its Id
 * @param id
 * @return
 * @throws ProductServiceException
 * @throws ProductNotFoundException
 */
	ResponseEntity<String> deleteProduct(long id) throws ProductServiceException, ProductNotFoundException;

}