package com.rakuten.rakutenproductservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.exception.CurrencyDaoException;
import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyServiceException;
import com.rakuten.rakutenproductservice.exception.ProductNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductServiceException;
import com.rakuten.rakutenproductservice.product.service.ProductService;

import io.swagger.annotations.Api;
/**
 * Product Controller class
 * 
 * @author adarshsumma
 *
 */
@Controller
@RequestMapping("/api/")
@Api(value="Product", description="Operations pertaining to products in Online Store",tags = { "Product" })
public class ProductControllerImpl implements ProductController {
	
	@Autowired
	ProductService productService;
	
	/* (non-Javadoc)
	 * @see com.rakuten.rakutenproductservice.controller.ProductController#getProduct(long, java.lang.String)
	 */
	@Override
	@RequestMapping(value ="products/{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<ProductDto> getProduct( @PathVariable("id")  long id,@RequestParam(name = "currencyCode",required = false)String currencyCode) throws ProductServiceException, ProductNotFoundException, CurrencyNotFoundException {
		ProductDto product = null;
	    product = productService.getProduct(id,currencyCode);
        return ResponseEntity.ok(product);
    }
	
	/* (non-Javadoc)
	 * @see com.rakuten.rakutenproductservice.controller.ProductController#getProductCategories(long)
	 */
	@Override
	@RequestMapping(value ="products/{id}/categories", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<List<List<String>>> getProductCategories( @PathVariable("id")  long id) throws ProductServiceException, ProductNotFoundException {
		List<List<String>> categories = null;
		categories = productService.getProductCategories(id);
        return ResponseEntity.ok(categories);
    }
	
	/* (non-Javadoc)
	 * @see com.rakuten.rakutenproductservice.controller.ProductController#getProducts(java.lang.String)
	 */
	@Override
	@RequestMapping(value ="products", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(name="currencyCode", required = false)String currencyCode) throws ProductServiceException, CurrencyDaoException, CurrencyServiceException, CurrencyNotFoundException {
		List<ProductDto> products = null;
		products = productService.getProducts(currencyCode);
        return ResponseEntity.ok(products);
    }
	
	/* (non-Javadoc)
	 * @see com.rakuten.rakutenproductservice.controller.ProductController#createProduct(com.rakuten.rakutenproductservice.dto.ProductDto)
	 */
	@Override
	@RequestMapping(value ="products", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) throws ProductServiceException, CurrencyNotFoundException {
		ProductDto product = productService.createProduct(productDto);
        return ResponseEntity.ok(product);
    }
	
	
	/* (non-Javadoc)
	 * @see com.rakuten.rakutenproductservice.controller.ProductController#updateProduct(long, com.rakuten.rakutenproductservice.dto.ProductDto)
	 */
	@Override
	@RequestMapping(value ="products/{id}", method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<ProductDto> updateProduct( @PathVariable("id")  long id,@Valid @RequestBody ProductDto productDto) throws ProductServiceException, CurrencyNotFoundException, ProductNotFoundException {
		ProductDto product = productService.updateProduct(id,productDto);
       return ResponseEntity.ok(product);
   }
	
	/* (non-Javadoc)
	 * @see com.rakuten.rakutenproductservice.controller.ProductController#deleteProduct(long)
	 */
	@Override
	@RequestMapping(value ="products/{id}", method = RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
    public ResponseEntity<String> deleteProduct( @PathVariable("id")  long id) throws ProductServiceException, ProductNotFoundException {
	    productService.deleteProduct(id);
        return ResponseEntity.ok("Product successfully deleted");
    }
	
	
}
