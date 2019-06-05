package com.rakuten.rakutenproductservice.currency.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.rakutenproductservice.currency.dao.service.CurrencyDao;
import com.rakuten.rakutenproductservice.exception.CurrencyEntityNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyServiceException;
/**
 * Currency service implementation
 * 
 * @author adarshsumma
 *
 */
@Service("currencyServiceImpl")
public class CurrencyServiceImpl implements CurrencyService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);
	
	@Value("${conversion.rate.url}")
	String conversionUrl;
	
	@Autowired
	CurrencyDao CurrencyDao;
	
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Fetches target currency conversion rate based on currency code
	 * @throws CurrencyNotFoundException 
	 * 
	 */
	public Double getConversionRate(String targetCurrencyCode) throws CurrencyServiceException, CurrencyNotFoundException {
		Double conversionRate = null;
		try {
			CurrencyDao.getCurrencyByCurCode(targetCurrencyCode);
			final String url = conversionUrl+targetCurrencyCode;
			LOGGER.info("Currency Conversion rate URL:"+url);
		    RestTemplate restTemplate = new RestTemplate();
		    ResponseEntity<String> response= restTemplate. getForEntity(url, String.class);
			JsonNode jsonObj = mapper.readTree(response.getBody());
			 boolean isSuccess = jsonObj.get("success").asBoolean();
			    if(isSuccess) {
			    	JsonNode rates  = jsonObj.get("rates");
			    	conversionRate = rates.get(targetCurrencyCode).asDouble();
			    }else {
			    	throw new CurrencyServiceException("Failure to get conversion rate!");
			    }
		}
		catch(CurrencyEntityNotFoundException e) {
			LOGGER.error("Failure while getting currency details for a given currency code:"+targetCurrencyCode,e);
			throw new CurrencyNotFoundException("Currency with currencyCode:"+targetCurrencyCode+" does not exist!",e);
		} catch ( IOException e) {
			throw new CurrencyServiceException("Failure to get conversion rate!",e);
		}
	   
	    return conversionRate;
	}
}
