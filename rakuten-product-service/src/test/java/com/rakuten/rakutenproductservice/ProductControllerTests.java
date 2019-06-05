package com.rakuten.rakutenproductservice;

import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.rakuten.rakutenproductservice.controller.ProductControllerImpl;
import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.exception.ProductNotFoundException;
import com.rakuten.rakutenproductservice.product.service.ProductService;
/**
 * Test class for product controller
 * 
 * @author adarshsumma
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductControllerImpl.class)
public class ProductControllerTests {

	@Autowired
	private MockMvc mvc;
	@MockBean
	ProductService productService;

	@Before
	public void setup() {
	}

	@Test
	public void getProduct_test() throws Exception {
		ProductDto product = new ProductDto();
		product.setProductName("Test");
		given(productService.getProduct(1, "EUR")).willReturn(product);

		mvc.perform(get("/api/products/1?currencyCode=EUR").with(user("super").password("password"))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.productName").value("Test")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void getProduct_productNotFoundException_test() throws Exception {
		ProductDto product = new ProductDto();
		product.setProductName("Test");
		given(productService.getProduct(1, "EUR")).willThrow(new ProductNotFoundException());

		mvc.perform(get("/api/products/1?currencyCode=EUR").with(user("super").password("password"))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError())
				.andDo(MockMvcResultHandlers.print());
	}
}
