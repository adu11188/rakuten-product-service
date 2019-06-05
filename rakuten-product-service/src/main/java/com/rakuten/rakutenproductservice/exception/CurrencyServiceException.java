package com.rakuten.rakutenproductservice.exception;

public class CurrencyServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CurrencyServiceException() {

	}

	public CurrencyServiceException(String message) {
		super(message);
	}

	public CurrencyServiceException(Throwable cause) {
		super(cause);
	}

	public CurrencyServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
