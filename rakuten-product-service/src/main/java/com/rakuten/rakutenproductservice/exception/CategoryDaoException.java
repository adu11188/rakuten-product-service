package com.rakuten.rakutenproductservice.exception;
/**
 * DAO layer exception for Category
 * 
 * @author adarshsumma
 *
 */
public class CategoryDaoException extends BaseException {

	private static final long serialVersionUID = 1L;

	public CategoryDaoException() {

	}

	public CategoryDaoException(String message) {
		super(message);
	}

	public CategoryDaoException(Throwable cause) {
		super(cause);
	}

	public CategoryDaoException(String message, Throwable cause) {
		super(message, cause);
	}
}