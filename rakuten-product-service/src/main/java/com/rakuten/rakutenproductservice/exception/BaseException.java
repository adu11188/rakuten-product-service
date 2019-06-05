package com.rakuten.rakutenproductservice.exception;
/**
 * Base Exception class for product  application module
 * 
 * @author adarshsumma
 *
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	public BaseException() {

	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
