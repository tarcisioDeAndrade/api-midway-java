package com.api.adapter.in.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class HandlerIntegration<T> {
	
	@ExceptionHandler
	public ResponseEntity<String> handle(Exception e){
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		int errorCod = 500;
		
		String mensagem = "Exception - " + e.getMessage();
		
		log.error(mensagem);
		
		
		
		return ResponseEntity.status(httpStatus).body(mensagem) ;
	}

}
