package com.rakuten.rakutenproductservice.exception;

public class CurrencyDaoException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CurrencyDaoException() {

	}

	public CurrencyDaoException(String message) {
		super(message);
	}

	public CurrencyDaoException(Throwable cause) {
		super(cause);
	}

	public CurrencyDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}

