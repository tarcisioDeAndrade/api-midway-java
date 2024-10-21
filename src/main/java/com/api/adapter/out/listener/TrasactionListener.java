package com.api.adapter.out.listener;

import org.springframework.kafka.annotation.KafkaListener;

import com.api.application.port.in.TransactionFinishingPortIn;

public class TrasactionListener {
	
	  private TransactionFinishingPortIn transactionFinishingPortIn;
	  
	  public TrasactionListener(TransactionFinishingPortIn transactionFinishingPortIn) {
		  this.transactionFinishingPortIn = transactionFinishingPortIn;
	  }
	
	  @KafkaListener(topics = "transaction-topic", groupId = "group-1")
	  public void receiveMessage(String message) {
		  transactionFinishingPortIn.saveMessage(message);
	  }
}
