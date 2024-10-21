package com.api.adapter.out.persistence;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.adapter.out.entity.Transaction;
import com.api.adapter.out.repository.TransactionRepository;
import com.api.application.port.out.TransactionFinishingPortOut;

public class TransacationPersistence implements TransactionFinishingPortOut{
	
	protected static Logger log = LoggerFactory.getLogger(TransacationPersistence.class);
	private TransactionRepository  transactionRepository;
	
	public TransacationPersistence(TransactionRepository  transactionRepository) {
		this.transactionRepository = transactionRepository ;
	}

	@Override
	public void saveMessage(String value) {
		var entity = new Transaction(UUID.randomUUID(), value);
	        var transactionSaved = transactionRepository.save(entity);
	        log.info(transactionSaved.getTransactionId().toString());
	}

}
