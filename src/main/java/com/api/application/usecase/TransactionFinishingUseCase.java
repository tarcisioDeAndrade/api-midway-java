package com.api.application.usecase;

import com.api.application.port.in.TransactionFinishingPortIn;
import com.api.application.port.out.TransactionFinishingPortOut;

public class TransactionFinishingUseCase implements TransactionFinishingPortIn{

	private TransactionFinishingPortOut transaction;
	
	public TransactionFinishingUseCase(TransactionFinishingPortOut transaction) {
		this.transaction = transaction;
	}
	
	@Override
	public void saveMessage(String value) {
		transaction.saveMessage(value);
	}
 
}
