package com.rakuten.rakutenproductservice.product.service;

import java.util.List;

import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductServiceException;
/**
 * Product Service
 * @author adarshsumma
 *
 */
public interface ProductService {
	ProductDto getProduct(long id,String currencyCode) throws ProductServiceException, ProductNotFoundException, CurrencyNotFoundException ;
	List<ProductDto> getProducts(String currencyCode) throws CurrencyNotFoundException, ProductServiceException ;
	ProductDto createProduct(ProductDto product) throws ProductServiceException, CurrencyNotFoundException;
	ProductDto updateProduct(Long productId,ProductDto product) throws CurrencyNotFoundException, ProductServiceException, ProductNotFoundException;
	void  deleteProduct(long id) throws ProductServiceException, ProductNotFoundException;
	List<List<String>> getProductCategories(long id) throws ProductServiceException, ProductNotFoundException ;
	
}
