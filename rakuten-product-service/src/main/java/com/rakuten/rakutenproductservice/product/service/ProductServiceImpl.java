package com.rakuten.rakutenproductservice.product.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.rakutenproductservice.category.dao.service.CategoryDao;
import com.rakuten.rakutenproductservice.currency.dao.service.CurrencyDao;
import com.rakuten.rakutenproductservice.currency.service.CurrencyService;
import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.dto.ProductDto.Currency;
import com.rakuten.rakutenproductservice.entities.Category;
import com.rakuten.rakutenproductservice.entities.CategoryGroup;
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
import com.rakuten.rakutenproductservice.util.MapperUtil;
/**
 * Product service implementation class
 * 
 * @author adarshsumma
 *
 */
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	CurrencyDao currencyDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	CurrencyService currencyService;
	/**
	 * Fetch product DTO for a given product Id and currency code
	 * @throws CurrencyNotFoundException 
	 * 
	 */
	@Override
	public ProductDto getProduct(long id, String currencyCode) throws ProductServiceException, ProductNotFoundException, CurrencyNotFoundException {
		Product product = null;
		ProductDto productDto = null;
		try {
			product = productDao.getProduct(id);
			Optional<String> cc = Optional.ofNullable(currencyCode);
			Double conversionFactor = 0.0d ;
			if(cc.isPresent()) {
				conversionFactor = currencyService.getConversionRate(currencyCode);
				com.rakuten.rakutenproductservice.entities.Currency currency = currencyDao.getCurrencyByCurCode(currencyCode);
				product.setCurrency(currency);
				product.setSalePrice(product.getSalePrice().multiply(new BigDecimal(conversionFactor)));
			}
			productDto = MapperUtil.prepareProductDto(product);
			
		} catch(ProductEntityNotFoundException e) {
			LOGGER.error("Failure while getting product details for a given id:"+id,e);
			throw new ProductNotFoundException("Product with id:"+id+" does not exist!",e);
			
		}catch (ProductDaoException | CurrencyServiceException e) {
			LOGGER.error("Failure while getting product details for a given id"+id,e);
			throw new ProductServiceException(e);
		} catch (CurrencyEntityNotFoundException e) {
			LOGGER.error("Failure while getting currency details for a product for given id"+id,e);
			throw new CurrencyNotFoundException();
		}
		return productDto ;
	}


/**
 * Get all products for a given currency code or default currency code
 * @throws CurrencyNotFoundException 
 * @throws ProductServiceException 
 */
	@Override
	public List<ProductDto> getProducts(String currencyCode) throws  CurrencyNotFoundException, ProductServiceException {
		List<Product> products = null;
		List<ProductDto> productDtos = new ArrayList<>();
		try {
			products = productDao.getProducts();
			
			Optional<String> cc = Optional.ofNullable(currencyCode);
			Double conversionFactor = 0.0d;
			com.rakuten.rakutenproductservice.entities.Currency currency = null;
			if(cc.isPresent()) {
				conversionFactor = currencyService.getConversionRate(currencyCode);
			    currency = currencyDao.getCurrencyByCurCode(currencyCode);
			}
			for (Product product : products) {
				if(cc.isPresent()) {
					product.setCurrency(currency);
					product.setSalePrice(product.getSalePrice().multiply(new BigDecimal(conversionFactor)));
				}
				productDtos.add(MapperUtil.prepareProductDto(product));
			}
		}catch (CurrencyDaoException | CurrencyServiceException e) {
			LOGGER.error("Failure while getting product details ",e);
			throw new ProductServiceException(e);
		} 
		return productDtos;
	}
/**
 * Create product for a given Product DTO
 * @throws CurrencyNotFoundException 
 * 
 */
	@Override
	@Transactional
	public ProductDto createProduct(ProductDto productDto) throws  ProductServiceException, CurrencyNotFoundException {
		Product product = null;
		Currency currencyDto = productDto.getCurrency();
		String currencyCode = currencyDto.getCurrencyCode();
		com.rakuten.rakutenproductservice.entities.Currency currency = null;
		currency = getCurrency(productDto, currencyCode, currency);
		Set<String> categoriesToBeSaved = mergeAndRemoveDuplicates(productDto.getCategoryFullPaths());
		List<Category> catgrs = getCategories(categoriesToBeSaved);
		List<Category> catgeoriesPresent = categoryDao.findByCategoryNameIn(categoriesToBeSaved);
		List<String> cp = catgeoriesPresent.stream().map(Category::getCategoryName).collect(Collectors.toList());
		List<Category> catgrsToCreate = catgrs.stream().filter(e->!cp.contains(e.getCategoryName())).collect(Collectors.toList());
		if(catgrs.size()>0) {
			catgrsToCreate = categoryDao.createCategories(catgrsToCreate);
		}
		catgrs.clear();
		/**
		 * Merge catgeories
		 */
		catgrs.addAll(catgrsToCreate);
		catgrs.addAll(catgeoriesPresent);
		List<CategoryGroup> categoryGroups = categoryDao.createCategoryGroups(getCategoryGroups(productDto));
		product = productDao.createProduct(MapperUtil.prepareProduct(productDto,currency,catgrs,categoryGroups));
		productDto = MapperUtil.prepareProductDto(product);
		return productDto;
	}
/**
 * Update product for given product DTO
 * 
 */
	@Override
	@Transactional
	public ProductDto updateProduct(Long productId, ProductDto productDto) throws CurrencyNotFoundException, ProductServiceException, ProductNotFoundException {
		Product product;
		try {
			Currency currencyDto = productDto.getCurrency();
			String currencyCode = currencyDto.getCurrencyCode();
			com.rakuten.rakutenproductservice.entities.Currency currency = null;
			currency = getCurrency(productDto, currencyCode, currency);
			Set<String> categoriesToBeSaved = mergeAndRemoveDuplicates(productDto.getCategoryFullPaths());
			List<Category> catgrs = getCategories(categoriesToBeSaved);
			List<Category> catgeoriesPresent = categoryDao.findByCategoryNameIn(categoriesToBeSaved);
			List<String> cp = catgeoriesPresent.stream().map(Category::getCategoryName).collect(Collectors.toList());
			List<Category> catgrsToCreate = catgrs.stream().filter(e->!cp.contains(e.getCategoryName())).collect(Collectors.toList());
			if(catgrs.size()>0) {
				catgrsToCreate = categoryDao.createCategories(catgrsToCreate);
			}
			catgrs.clear();
			catgrs.addAll(catgrsToCreate);
			catgrs.addAll(catgeoriesPresent);
			List<CategoryGroup> categoryGroups = categoryDao.createCategoryGroups(getCategoryGroups(productDto));
			product = MapperUtil.prepareProduct(productDto,currency,catgrs,categoryGroups);
			product.setProductId(productId);
			product = productDao.updateProduct(product);
			productDto = MapperUtil.prepareProductDto(product);
		} catch(ProductEntityNotFoundException e) {
			LOGGER.error("Failure while updating product details for a given id:"+productId,e);
			throw new ProductNotFoundException("Product with id:"+productId+" does not exist!",e);
			
		}catch (ProductDaoException e) {
			LOGGER.error("Failure while updating product details for a given id"+productId,e);
			throw new ProductServiceException(e);
		}
		return productDto;
	}


/**
 * Helper function to get list of category entities
 * 
 * @param productDto
 * @param categories
 * @return
 */
	private List<Category> getCategories(Set<String> categories) {
		List<Category> catgrs = new ArrayList<>();
		for(String category:categories) {
			Category c = new Category();
			c.setCategoryName(category);
			catgrs.add(c);
		}
		return catgrs;
	}


/**
 * Helper function to merge and remove duplicate values
 * @param clist
 * @return
 */
	private Set<String> mergeAndRemoveDuplicates(List<List<String>> clist) {
		Set<String> values = new HashSet<>();
		if(clist==null)return values;
		for(List<String> c:clist) {
			for(String value:c) {
				values.add(value);
			}
		}
		return values;
	}


	/**
	 * Create category groups
	 * 
	 * @param productDto
	 * @return
	 */
	private List<CategoryGroup> getCategoryGroups(ProductDto productDto){
		List<CategoryGroup> catgrps = new ArrayList<>();
		if(productDto==null)return catgrps;
		Optional<List<List<String>>> cgs = Optional.ofNullable(productDto.getCategoryFullPaths()) ;
		if(cgs.isPresent()) {
			for(@SuppressWarnings("unused") List<String> cg:cgs.get()) {
				CategoryGroup catGrp = new CategoryGroup();
				catGrp.setCategoryGroupName(new Date().toString());
				catgrps.add(catGrp);
			}
			catgrps = categoryDao.createCategoryGroups(catgrps);
		}
		return catgrps;
	}

	/**
	 * Helper function to fetch currency entity for a given currency code
	 * 
	 * @param productDto
	 * @param currencyCode
	 * @param currency
	 * @return
	 * @throws CurrencyNotFoundException
	 * @throws ProductServiceException
	 */
	private com.rakuten.rakutenproductservice.entities.Currency getCurrency(ProductDto productDto, String currencyCode,
			com.rakuten.rakutenproductservice.entities.Currency currency)
			throws CurrencyNotFoundException, ProductServiceException {
		try {
			currency = currencyDao.getCurrencyByCurCode(productDto.getCurrency().getCurrencyCode());
		}catch(CurrencyEntityNotFoundException e) {
			LOGGER.error("Failure while getting currency details for a given currency code:"+currencyCode,e);
			throw new CurrencyNotFoundException("Currency with currencyCode:"+currencyCode+" does not exist!",e);
			
		} 
		return currency;
	}

	/**
	 * Delete a product based on its product Id
	 */
	@Override
	public void deleteProduct(long id) throws ProductServiceException, ProductNotFoundException {
		try {
			productDao.deleteProduct(id);
		} catch(ProductEntityNotFoundException e) {
			LOGGER.error("Failure while deleting product for a given id:"+id,e);
			throw new ProductNotFoundException("Product with id:"+id+" does not exist!",e);
		}catch (ProductDaoException e) {
			LOGGER.error("Failure while deleting product  for a given id"+id,e);
			throw new ProductServiceException(e);
		}
	}


/**
 * Fetch categories for a given product Id
 */
	@Override
	public List<List<String>> getProductCategories(long id) throws ProductServiceException, ProductNotFoundException {
		Product product = null;
		ProductDto productDto = null;
		try {
			product = productDao.getProduct(id);
			productDto = MapperUtil.prepareProductDto(product);
		} catch(ProductEntityNotFoundException e) {
			LOGGER.error("Failure while getting product details for a given id:"+id,e);
			throw new ProductNotFoundException("Product with id:"+id+" does not exist!",e);
		}catch (ProductDaoException e) {
			LOGGER.error("Failure while getting product details for a given id"+id,e);
			throw new ProductServiceException(e);
		}
		return productDto.getCategoryFullPaths() ;
	}

}
