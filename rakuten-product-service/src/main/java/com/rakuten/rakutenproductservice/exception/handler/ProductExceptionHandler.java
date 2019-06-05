package com.rakuten.rakutenproductservice.exception.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rakuten.rakutenproductservice.exception.CategoryNotFoundException;
import com.rakuten.rakutenproductservice.exception.CurrencyNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductNotFoundException;
import com.rakuten.rakutenproductservice.exception.ProductServiceException;
/**
 * Exception handling for controller
 * 
 * @author adarshsumma
 *
 */
@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

	  @ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	  @ExceptionHandler(ProductNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleProductNotFoundException(ProductNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }

	  @ExceptionHandler(CurrencyNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleCurrencyNotFoundException(CurrencyNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	  
	  @ExceptionHandler(CategoryNotFoundException.class)
	  public final ResponseEntity<ErrorDetails> handleCategoryNotFoundException(CurrencyNotFoundException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	  @ExceptionHandler(ProductServiceException.class)
	  public final ResponseEntity<ErrorDetails> handleProductServiceExceptions(Exception ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	  
	  @Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Input Validation Failed",
	        ex.getBindingResult().toString());
	    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	}
