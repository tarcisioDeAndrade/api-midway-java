package com.api.adapter.in.http.exception;

import org.apache.commons.lang3.StringUtils;

public abstract class TransactionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private int errorCode;
	private int httpCode;
	private String messageId;
	
	public TransactionException(String title, String msg, int errorCode, int httpCode) {
		super(msg);
		this.title = title;
		this.errorCode = errorCode;
		this.httpCode = httpCode;
	}
	
	public TransactionException(String title, String msg, int errorCode, int httpCode, Throwable e ) {
		super(msg, e);
		this.title = title;
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		
	}
	
	
	public TransactionException(String title, String msg,String msgId, int errorCode, int httpCode) {
		super(msg);
		this.title = title;
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		this.messageId = msgId;
	}
	
	public TransactionException(String title, int errorCode, int httpCode, String messageId) {
		super();
		this.title = title;
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		this.messageId = messageId;
	}
	
	
	public String getTitle() {
		return title;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public int getHttpCode() {
		return httpCode;
	}
	public String getMessageId() {
		if(StringUtils.isBlank(messageId)) {
			return super.getMessage();
		}
		return messageId;
	}
	
	
	
	
	

}
