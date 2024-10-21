package com.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.api.adapter.out.persistence.TransacationPersistence;
import com.api.adapter.out.producer.TransactionProducer;
import com.api.adapter.out.repository.TransactionRepository;
import com.api.application.port.in.TransactionFinishingPortIn;
import com.api.application.port.in.TransactionPortIn;
import com.api.application.port.out.TransactionFinishingPortOut;
import com.api.application.port.out.TransactionPortOut;
import com.api.application.usecase.TransactionFinishingUseCase;
import com.api.application.usecase.TransactionUseCase;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class ConfigurationApplication {
	
	private GenericWebApplicationContext context;
	
	@Autowired
	public ConfigurationApplication(GenericWebApplicationContext context) {
		log.info("Iniciando Configuration");
		this.context = context;
	}
	
	@Bean
	public TransactionPortIn transactionPortIn() {
		TransactionPortOut transactionPortOut = transactionPortOut();
		TransactionPortIn transactionPortIn = new TransactionUseCase(transactionPortOut);
		log.info("Bean TransactionPortIn successfully configured.");
		return transactionPortIn;
	}
	
	@Bean 
	public TransactionPortOut transactionPortOut() {
		KafkaTemplate<String, String> kafkaTemplate = context.getBean(KafkaTemplate.class);
		TransactionPortOut transactionPortOut = new TransactionProducer(kafkaTemplate);
		log.info("Bean TransactionPortOut successfully configured.");
		return transactionPortOut;
	}
	
	@Bean
	public TransactionFinishingPortIn transactionFinishingPortIn() {
		TransactionFinishingPortOut transactionFinishingPortOut = transactionFinishingPortOut();
		TransactionFinishingPortIn transactionFinishingPortIn = new TransactionFinishingUseCase(transactionFinishingPortOut);
		log.info("Bean TransactionFinishingPortIn successfully configured.");
		return transactionFinishingPortIn;
	}
	
	@Bean 
	public TransactionFinishingPortOut transactionFinishingPortOut() {
		TransactionRepository  transactionRepository = context.getBean(TransactionRepository.class);
		TransactionFinishingPortOut transactionFinishingPortOut = new TransacationPersistence(transactionRepository);
		log.info("Bean TransactionFinishingPortOut successfully configured.");
		return transactionFinishingPortOut;
	}

}
