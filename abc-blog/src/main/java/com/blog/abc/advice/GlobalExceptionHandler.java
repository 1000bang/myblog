package com.blog.abc.advice;



import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.abc.dto.CustomError;
import com.blog.abc.dto.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	
	/*
	 * private String statusCode;
private String requestUri;
private int code;
private String message;
private String resultCode;
private List<CustomError> errorList;
	 * 
	 */
	

public ResponseEntity<?> exception(Exception e) {
		
		System.out.println("--------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getLocalizedMessage());
		System.out.println("--------------");
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
	}
	

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage() + "</h1>";
		
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
		
		System.out.println("MethodArgumentNotValidException");
		
		List<CustomError> eList = new ArrayList<>();
		BindingResult bindingResult = e.getBindingResult();
		bindingResult.getAllErrors().forEach(action -> {
			FieldError fieldError = (FieldError) action;
			String fieldName = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			CustomError customError = new CustomError();
			customError.setField(fieldName);
			customError.setMessage(message);
			eList.add(customError);		
		});
		
		//Todo
		//error Response s??? ???????????? 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eList);
		
	}
	
	@ExceptionHandler(value = UnexpectedRollbackException.class)
	public ResponseEntity unexpectedRollbackException(UnexpectedRollbackException e) {
		
		System.out.println("UnexpectedRollbackException");
		
		ErrorResponse errorResponse = ErrorResponse
				.builder()
				.statusCode(HttpStatus.BAD_REQUEST.toString())
				.code(HttpStatus.BAD_REQUEST.value())
				.message("????????? ???????????? ???????????????. ")
				.build();

		
		

		
		//Todo
		//error Response s??? ???????????? 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		
	}


}
