package com.api.adapter.in.http.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.adapter.in.http.transaction.dto.TransactionRequestDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(value = "/transaction")
@Api(value = "Transaction API")
interface TransactionApi {

	
	@PostMapping
	@ApiOperation(value ="Fazer login do usuario no sistema")
	public ResponseEntity<String> login(@RequestBody TransactionRequestDTO request) throws Exception;
}
