package com.rakuten.rakutenproductservice.exception;
/**
 * Category entity not found exception
 * 
 * @author adarshsumma
 *
 */
public class CategoryEntityNotFoundException extends CategoryDaoException{

	private static final long serialVersionUID = 1L;

	public CategoryEntityNotFoundException() {
	}

	public CategoryEntityNotFoundException(String message) {
		super(message);
	}

	public CategoryEntityNotFoundException(Throwable cause) {
		super(cause);
	}

	public CategoryEntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}

