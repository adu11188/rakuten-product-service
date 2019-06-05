package com.rakuten.rakutenproductservice.exception;

public class CurrencyEntityNotFoundException extends CurrencyDaoException{

	private static final long serialVersionUID = 1L;

	public CurrencyEntityNotFoundException() {
	}

	public CurrencyEntityNotFoundException(String message) {
		super(message);
	}

	public CurrencyEntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public CurrencyEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
