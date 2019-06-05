package com.rakuten.rakutenproductservice.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.rakuten.rakutenproductservice.dto.ProductDto;
import com.rakuten.rakutenproductservice.dto.ProductDto.ProductDtoBuilder;
import com.rakuten.rakutenproductservice.entities.Category;
import com.rakuten.rakutenproductservice.entities.CategoryGroup;
import com.rakuten.rakutenproductservice.entities.CategoryGroupMapping;
import com.rakuten.rakutenproductservice.entities.CategoryGroupMappingPK;
import com.rakuten.rakutenproductservice.entities.Currency;
import com.rakuten.rakutenproductservice.entities.Product;
import com.rakuten.rakutenproductservice.entities.ProductCategoryGroupMapping;
/**
 * Helper class
 * 
 * @author adarshsumma
 *
 */
public class MapperUtil {

	private MapperUtil() {
	}

	/**
	 * Form the prodcut DTO object using the Product entity
	 * 
	 * @param product
	 * @return
	 */
	public static ProductDto prepareProductDto(Product product) {
		ProductDto productDto;
		List<ProductCategoryGroupMapping> pcgms = product.getProductCategoryGroupMappings();
		List<List<String>> fullCategoryPaths = new ArrayList<>();
		if (pcgms!=null) {
			for (ProductCategoryGroupMapping pcgm : pcgms) {

				List<CategoryGroupMapping> cgms = pcgm.getCategoryGroup().getCategoryGroupMappings();

				cgms.sort(new Comparator<CategoryGroupMapping>() {
					@Override
					public int compare(CategoryGroupMapping o1, CategoryGroupMapping o2) {
						return o1.getId().getSequenceNo().compareTo(o2.getId().getSequenceNo());
					}
				});
				List<String> categoryFullPath = new ArrayList<>();
				for (CategoryGroupMapping cgm : cgms) {
					String categoryName = cgm.getCategory().getCategoryName();
					categoryFullPath.add(categoryName);
				}
				fullCategoryPaths.add(categoryFullPath);
			} 
		}
		Currency cur = product.getCurrency();
		ProductDtoBuilder productDtoBuilder = new ProductDtoBuilder();
		productDtoBuilder.setAvailable(product.getAvailable()).setBrand(product.getBrand()).setColor(product.getColor())
				.setDescription(product.getDescription()).setGender(product.getGender())
				.setProductName(product.getProductName())
				.setSalePrice(product.getSalePrice()).setCategoryFullPaths(fullCategoryPaths);
		if (cur != null) {
			productDtoBuilder.setCurencyName(product.getCurrency().getCurrencyName())
					.setCurrencyCode(product.getCurrency().getCurrencyCode())
					.setCurrencySymbol(product.getCurrency().getCurrencySymbol());
		}
		productDto = productDtoBuilder.build();
		return productDto;
	}
	/**
	 * Form Product entity using ProductDto
	 * 
	 * @param productDto
	 * @param categories
	 * @param categoryGroups
	 * @return
	 */
	public static Product prepareProduct(ProductDto productDto,List<Category> categories,List<CategoryGroup> categoryGroups) {
		Product product = new Product();
		product.setBrand(productDto.getBrand());
		product.setAvailable(productDto.isAvailable());
		product.setColor(productDto.getColor());
		product.setDescription(productDto.getDescription());
		product.setGender(productDto.getGender());
		product.setProductName(productDto.getProductName());
		product.setSalePrice(productDto.getSalePrice());
		List<ProductCategoryGroupMapping> productCategoryGroupMappings = new ArrayList<>();
		product.setProductCategoryGroupMappings(productCategoryGroupMappings);
		List<List<String>> pcfps = productDto.getCategoryFullPaths();
		for(int i=0;i<pcfps.size();i++) {
			List<String> pcfp=pcfps.get(i);
			ProductCategoryGroupMapping pcgm =new ProductCategoryGroupMapping();
			pcgm.setProduct(product);
			CategoryGroup cg = categoryGroups.get(i);
			cg.setProductCategoryGroupMappings(productCategoryGroupMappings);
			cg.addProductCategoryGroupMapping(pcgm);
			pcgm.setCategoryGroup(cg);
			
			List<CategoryGroupMapping> cgms = new ArrayList<>();
			cg.setCategoryGroupMappings(cgms);
			int seqNo = 1;
			
			
			for (String cName : pcfp) {
				CategoryGroupMapping cgm = new CategoryGroupMapping();
				cg.addCategoryGroupMapping(cgm);
				Optional<Category> matchingCategory = categories.stream().filter(p -> p.getCategoryName().equals(cName))
						.findFirst();
				Category matchedCategory = matchingCategory.get();
				cgm.setCategory(matchedCategory);
				cgm.setCategoryGroup(cg);
				CategoryGroupMappingPK cgmPK = new CategoryGroupMappingPK();
				cgmPK.setSequenceNo(seqNo++);
				cgm.setId(cgmPK);
				cgmPK.setCategoryGroupId(cg.getCategoryGroupId());
				cgmPK.setCategoryId(matchedCategory.getCategoryId());
			}
			
		}
		
		return product;
		
	}
	/**
	 * Prepare product entity
	 * 
	 * @param productDto
	 * @param currency
	 * @param categories
	 * @param categoryGroups
	 * @param productId
	 * @return
	 */
	public static Product prepareProduct(ProductDto productDto,Currency currency,List<Category> categories,List<CategoryGroup> categoryGroups,Long productId) {
		Product product = prepareProduct(productDto,currency,categories, categoryGroups);
		product.setProductId(productId);
		return product;
	}
	/**
	 * Prepare product entity
	 * @param productDto
	 * @param currency
	 * @param categories
	 * @param categoryGroups
	 * @return
	 */
	public static Product prepareProduct(ProductDto productDto,Currency currency,List<Category> categories,List<CategoryGroup> categoryGroups) {
		Product product = prepareProduct(productDto,categories,categoryGroups);
		product.setCurrency(currency);
		return product;
	}

}
