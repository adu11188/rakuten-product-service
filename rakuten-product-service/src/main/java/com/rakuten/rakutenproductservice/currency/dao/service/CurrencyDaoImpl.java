package com.rakuten.rakutenproductservice.currency.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rakuten.rakutenproductservice.entities.Currency;
import com.rakuten.rakutenproductservice.exception.CurrencyDaoException;
import com.rakuten.rakutenproductservice.exception.CurrencyEntityNotFoundException;
import com.rakuten.rakutenproductservice.repo.CurrencyRepository;
/**
 * 
 * @author adarshsumma
 *
 */
@Repository("CurrencyDaoImpl")
public class CurrencyDaoImpl implements CurrencyDao {

	@Autowired
	CurrencyRepository currencyRepo;
		
	@Override
	public Currency getCurrency(long id) throws CurrencyEntityNotFoundException {
		Currency currency = null;
		currency = currencyRepo.findById(id).orElseThrow(()->new CurrencyEntityNotFoundException("Currency does not exist"));
		return currency;
	}

	@Override
	public List<Currency> getCurrencies() {
		return currencyRepo.findAll();
	}

	@Override
	public Currency createCurrency(Currency currency) {
		currency = currencyRepo.save(currency);
		return currency;

	}

	@Override
	public Currency updateCurrency(Currency currency) throws CurrencyDaoException {
		try {
			if(currencyRepo.findById(currency.getCurrencyId()). isPresent()) {
				currency = currencyRepo.save(currency);
				return currency;
			}else {
				throw new CurrencyEntityNotFoundException("Currency does not exist");
			}
		}catch(IllegalArgumentException  e) {
			throw new CurrencyDaoException("Currency id is null",e);
		}
		
	}

	
	@Override
	public void deleteCurrency(long id) throws CurrencyDaoException {
		try {
			currencyRepo.deleteById(id);
		}catch(IllegalArgumentException  e) {
			throw new CurrencyDaoException("Currency id is null",e);
		}
		
	}

	@Override
	public Currency getCurrencyByCurCode(String currencyCode) throws CurrencyEntityNotFoundException {
		Currency currency = null;
			currency = currencyRepo.findByCurrencyCode(currencyCode);
			if(currency==null)
			 throw new CurrencyEntityNotFoundException("Currency does not exist");
		return currency;
	}

}
