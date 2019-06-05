package com.rakuten.rakutenproductservice.exception;

public class ProductServiceException extends BaseException {

	private static final long serialVersionUID = 1L;

	public ProductServiceException() {

	}

	public ProductServiceException(String message) {
		super(message);
	}

	public ProductServiceException(Throwable cause) {
		super(cause);
	}

	public ProductServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
