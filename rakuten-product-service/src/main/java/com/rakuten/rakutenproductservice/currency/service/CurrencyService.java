package com.rakuten.rakutenproductservice.currency.service;

import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyServiceException;
/**
 * Currency service interface
 * 
 * @author adarshsumma
 *
 */
public interface CurrencyService {
	public Double getConversionRate(String targetCurrencyCode) throws CurrencyServiceException, CurrencyNotFoundException;
}
