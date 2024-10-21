package com.api.adapter.out.producer;

import org.springframework.kafka.core.KafkaTemplate;

import com.api.application.port.out.TransactionPortOut;

public class TransactionProducer implements TransactionPortOut {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate) {
		    this.kafkaTemplate = kafkaTemplate;
     }

	@Override
	public void sendMenssage(String value) {
		kafkaTemplate.send("transaction-topic", value);
	}

}
