package com.api.adapter.in.http.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.api.adapter.in.http.ControllerExecutor;
import com.api.adapter.in.http.transaction.dto.TransactionRequestDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TransactionAdpterIn implements TransactionApi {
	private static final String CONTROLLER_TRANSACTION = "Controller Transaction";

	@Override
	public ResponseEntity<String> login(TransactionRequestDTO request) throws Exception {
		// TODO Auto-generated method stub
		return ControllerExecutor.of(CONTROLLER_TRANSACTION).addExecution(() -> {
			String urlEncoded = "Entrou !!";
			
			return ResponseEntity.ok(urlEncoded);
			
		}).onLoginTransactionError( e -> e.getMessage())
		  .onError(e -> e.getMessage())
		  .doIt();
	}

}
