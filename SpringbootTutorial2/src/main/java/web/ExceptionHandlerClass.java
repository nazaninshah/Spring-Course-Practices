package web;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import web.exception.BookIdMismatchException;
import web.exception.BookNotFoundException;

@ControllerAdvice
public class ExceptionHandlerClass extends ResponseEntityExceptionHandler{

	public ExceptionHandlerClass() {
		super();
	}
	
	@ExceptionHandler(BookNotFoundException.class)
	protected ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request){
		return handleExceptionInternal(ex, "Book does not exist", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({
	      BookIdMismatchException.class,
	      ConstraintViolationException.class,
	      DataIntegrityViolationException.class
	    })
	    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
	        return handleExceptionInternal(ex, ex
	          .getLocalizedMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	    }
}
