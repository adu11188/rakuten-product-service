package com.rakuten.rakutenproductservice.exception;
/**
 * Category not found exception
 * 
 * @author adarshsumma
 *
 */
public class CategoryNotFoundException extends BaseException{
	private static final long serialVersionUID = 1L;

	public CategoryNotFoundException() {
	}
	public CategoryNotFoundException(String message) {
		super(message);
	}
	public CategoryNotFoundException(Throwable cause) {
		super(cause);
	}
	public CategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}