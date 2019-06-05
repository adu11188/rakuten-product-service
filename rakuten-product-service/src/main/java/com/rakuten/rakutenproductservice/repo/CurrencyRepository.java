package com.rakuten.rakutenproductservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rakuten.rakutenproductservice.entities.Currency;
/**
 * Currency repository
 * 
 * @author adarshsumma
 *
 */
public interface CurrencyRepository extends JpaRepository<Currency,Long>{
	Currency findByCurrencyCode(String currencyCode);
}
