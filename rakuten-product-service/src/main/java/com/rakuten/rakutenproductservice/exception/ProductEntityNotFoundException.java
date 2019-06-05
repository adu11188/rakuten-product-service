package com.rakuten.rakutenproductservice.exception;

public class ProductEntityNotFoundException extends ProductDaoException{

	private static final long serialVersionUID = 1L;

	public ProductEntityNotFoundException() {
	}

	public ProductEntityNotFoundException(String message) {
		super(message);
	}

	public ProductEntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProductEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
