package com.rakuten.rakutenproductservice.exception;

public class CurrencyNotFoundException extends BaseException{
	private static final long serialVersionUID = 1L;

	public CurrencyNotFoundException() {
	}
	public CurrencyNotFoundException(String message) {
		super(message);
	}
	public CurrencyNotFoundException(Throwable cause) {
		super(cause);
	}
	public CurrencyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}