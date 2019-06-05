package com.rakuten.rakutenproductservice.currency.dao.service;

import java.util.List;

import com.rakuten.rakutenproductservice.entities.Currency;
import com.rakuten.rakutenproductservice.exception.CurrencyDaoException;
import com.rakuten.rakutenproductservice.exception.CurrencyEntityNotFoundException;
/**
 * Currency DAO interface
 * 
 * Wrapper for Currency Repository
 * 
 * @author adarshsumma
 *
 */
public interface CurrencyDao {

	Currency getCurrency(long id) throws CurrencyEntityNotFoundException ;
	Currency getCurrencyByCurCode(String currencyCode) throws CurrencyEntityNotFoundException ;
	List<Currency> getCurrencies() ;
	Currency createCurrency(Currency currency) ;
	Currency updateCurrency(Currency currency) throws CurrencyDaoException ;
	void  deleteCurrency(long id) throws CurrencyDaoException;
	
	
}
