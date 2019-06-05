package com.rakuten.rakutenproductservice.exception;

public class ProductDaoException  extends BaseException {

	private static final long serialVersionUID = 1L;

	public ProductDaoException() {

	}

	public ProductDaoException(String message) {
		super(message);
	}

	public ProductDaoException(Throwable cause) {
		super(cause);
	}

	public ProductDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
