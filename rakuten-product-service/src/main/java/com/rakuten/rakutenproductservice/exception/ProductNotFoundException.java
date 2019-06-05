package com.rakuten.rakutenproductservice.exception;

public class ProductNotFoundException  extends BaseException{
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {

	}

	public ProductNotFoundException(String message) {
		super(message);
	}

	public ProductNotFoundException(Throwable cause) {
		super(cause);
	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
