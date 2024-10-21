package com.api.application.usecase;

import com.api.application.port.in.TransactionPortIn;
import com.api.application.port.out.TransactionPortOut;

public class TransactionUseCase implements TransactionPortIn {
	
	private TransactionPortOut transaction;
	
	public TransactionUseCase (TransactionPortOut transaction) {
		this.transaction = transaction;
	}

	@Override
	public void transactionAction(String value) throws Exception {
        transaction.sendMenssage(value); 
	}
}
