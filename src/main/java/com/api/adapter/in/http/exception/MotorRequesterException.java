package com.api.adapter.in.http.exception;

import java.util.concurrent.TimeoutException;

public abstract class MotorRequesterException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	
	private String title;
	private int errorCode;
	private int httpCode;
	private String detailError;
	
	public MotorRequesterException(String title, int errorCode, int httpCode, String detailError) {
		super(getFormatMsgException(title, title, detailError));
		this.title = title;
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		this.detailError = detailError;
	}
	
	public MotorRequesterException(String title, int errorCode, int httpCode, String detailError, Throwable e) {
		super(getFormatMsgException(title, title, detailError), e);
		this.title = title;
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		this.detailError = detailError;
	}


	public MotorRequesterException(String title, String msg, String detailError, int errorCode, int httpCode, Throwable e) {
		super(getFormatMsgException(title, title, detailError), e);
		this.errorCode = errorCode;
		this.title = title;
		this.httpCode = httpCode;
		this.detailError = detailError;
	}
	
	public MotorRequesterException(String title, String msg, String detailError, int errorCode, int httpCode) {
		super(getFormatMsgException(title, title, detailError));
		this.errorCode = errorCode;
		this.title = title;
		this.httpCode = httpCode;
		this.detailError = detailError;
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

	public String getDetailError() {
		return detailError;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	private static String getFormatMsgException(String title, String msg, String detalhe) {
		return String.format("'%s' - msg: %s - detalhe: %s", title, msg, detalhe);
	}
	
	
	

}
