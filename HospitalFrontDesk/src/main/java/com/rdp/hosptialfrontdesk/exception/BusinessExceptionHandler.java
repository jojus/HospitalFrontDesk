package com.rdp.hosptialfrontdesk.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.rdp.hosptialfrontdesk.constants.ApplicationConstants.INVALID_HOSPITAL_NAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rdp.hosptialfrontdesk.model.ExceptionResonse;
import com.rdp.hosptialfrontdesk.model.InvalidHospitalNameException;

@ControllerAdvice
public class BusinessExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * 
	 * @param hospitalNameException
	 * @return ResponseEntity object
	 */
	public final ResponseEntity<ExceptionResonse> buildResponseEntityForInvalidHospitalNameException(
			InvalidHospitalNameException hospitalNameException) {

		ExceptionResonse exceptionResponse = new ExceptionResonse(hospitalNameException.getMessage(),
				INVALID_HOSPITAL_NAME);
		return new ResponseEntity<ExceptionResonse>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);

	}
	
	
	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<Object> handle(Exception exception, 
	                HttpServletRequest request, HttpServletResponse response) {
	        if (exception instanceof NullPointerException) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	
}
