package com.rakuten.rakutenproductservice;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rakuten.rakutenproductservice.currency.dao.service.CurrencyDao;
import com.rakuten.rakutenproductservice.currency.service.CurrencyService;
import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.entities.Currency;
import com.rakuten.rakutenproductservice.entities.Product;
import com.rakuten.rakutenproductservice.exception.CurrencyDaoException;
import com.rakuten.rakutenproductservice.exception.CurrencyEntityNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyServiceException;
import com.rakuten.rakutenproductservice.exception.ProductDaoException;
import com.rakuten.rakutenproductservice.exception.ProductEntityNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductServiceException;
import com.rakuten.rakutenproductservice.product.dao.service.ProductDao;
import com.rakuten.rakutenproductservice.product.service.ProductServiceImpl;
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTests {

	
	@InjectMocks
    private ProductServiceImpl productService;
	
	@Mock
	CurrencyService currencyService;
	@Mock
	CurrencyDao currencyDao;
	
	@Mock
    private ProductDao productDao;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProduct_test_success() throws CurrencyServiceException, CurrencyNotFoundException, ProductEntityNotFoundException, ProductDaoException, ProductServiceException, ProductNotFoundException, CurrencyDaoException {
		Product product = new Product();
		product.setSalePrice(new BigDecimal(1));
		given(currencyService.getConversionRate("EUR")).willReturn(1.0d);
		Currency currency = new Currency();
		given(currencyDao.getCurrencyByCurCode("EUR")).willReturn(currency);
		given(productDao.getProduct(1)).willReturn(product);
		ProductDto p = productService.getProduct(1, "EUR");
		assertNotNull(p);
	}
	
	@Test(expected =  ProductNotFoundException.class)
	public void getProduct_prodnotfoundException_test() throws CurrencyServiceException, CurrencyNotFoundException, ProductEntityNotFoundException, ProductDaoException, ProductServiceException, ProductNotFoundException, CurrencyDaoException {
		Product product = new Product();
		product.setSalePrice(new BigDecimal(1));
		given(currencyService.getConversionRate("EUR")).willReturn(1.0d);
		Currency currency = new Currency();
		given(currencyDao.getCurrencyByCurCode("EUR")).willReturn(currency);
		given(productDao.getProduct(1)).willThrow(new ProductEntityNotFoundException());
		ProductDto p = productService.getProduct(1, "EUR");
		assertNotNull(p);
	}
	
	@Test(expected =  CurrencyNotFoundException.class)
	public void getProduct_currencynotfoundException_test() throws CurrencyServiceException, CurrencyNotFoundException, ProductEntityNotFoundException, ProductDaoException, ProductServiceException, ProductNotFoundException, CurrencyDaoException {
		Product product = new Product();
		product.setSalePrice(new BigDecimal(1));
		given(currencyService.getConversionRate("EUR")).willReturn(1.0d);
		given(currencyDao.getCurrencyByCurCode("EUR")).willThrow(new CurrencyEntityNotFoundException());
		given(productDao.getProduct(1)).willReturn(product);
		ProductDto p = productService.getProduct(1, "EUR");
		assertNotNull(p);
	}
	
}
