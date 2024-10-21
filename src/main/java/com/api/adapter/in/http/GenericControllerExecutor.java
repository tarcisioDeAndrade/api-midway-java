package com.api.adapter.in.http;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.api.adapter.in.http.exception.TransactionException;
import com.api.adapter.in.http.mapper.ContractParser;

public class GenericControllerExecutor<T> extends HandlerIntegration {
	
	protected static Logger LOGGER = LoggerFactory.getLogger(GenericControllerExecutor.class);
	
	private boolean printResp;
	private String title;
	
	private Callable<ResponseEntity<T>> executionController;
	
	private Consumer<TransactionException> executionLoginTadKeyError = (e) -> {};
	private Consumer<Exception> executionError = (e) -> {};
	
	protected GenericControllerExecutor(String title) {
		this.title = title;
	}
	
	public GenericControllerExecutor<T> addExecution(Callable<ResponseEntity<T>> executionController){
		this.executionController = executionController;
		return this;
	}
	
	public GenericControllerExecutor<T> onLoginTransactionError(Consumer<TransactionException> executionLoginTadKeyError){
		this.executionLoginTadKeyError = executionLoginTadKeyError;
		return this;
	}
	
	public GenericControllerExecutor<T> onError(Consumer<Exception> executionError){
		this.executionError = executionError;
		return this;
	}
	
	public ResponseEntity<T> doIt()throws Exception {
		try {
			LOGGER.info(String.format(">>>>>>>>>>>> Init execution controller %s  >>>>>>>>>>>>>", title));
			
			ResponseEntity<T> result = executionController.call();
			printResultToFront(result);
			
			LOGGER.info(String.format(">>>>>>>>>>>> End of execution controller %s >>>>>>>>>>>>", title));
			
			return result;
			
		}catch(Exception e) {
			LOGGER.error(String.format(">>>>>>>>>>>> Error execution controller %s >>>>>>>>>>>>", title));
			
			this.executionError.accept(e);
			return(ResponseEntity<T>) super.handle(e);
		}
		
	}
	
	private void printResultToFront(ResponseEntity<T> result) {
		if(printResp) {
			ContractParser parse = new ContractParser();
			String resultFront = parse.parseObj(result.getBody(), getClass());
			LOGGER.info(String.format("Controller %s return front %s", title, resultFront));
		}
	}
	
	public static <T> GenericControllerExecutor<T> of(String title){
		return new GenericControllerExecutor<T>(title);
	}
	
	public static <T> GenericControllerExecutor<T> of(String title, boolean printResponse){
		GenericControllerExecutor<T> controllerExecutor = of(title);
		controllerExecutor.printResp = printResponse;
		return controllerExecutor;
	}
 
}
