package com.api.adapter.in.http.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class InvalidContractX9Exception extends MotorRequesterException {

	private static final long serialVersionUID = 1L;
	private static final String title = "Erro Mudanca de Contrato";
	
	public InvalidContractX9Exception(String dtoName, String servico, String url, String motivo) {
		super(title, getMsg(dtoName, servico, url), motivo, 668, 500);
	}
	
	public InvalidContractX9Exception(String dtoName, String servico, String url, JsonProcessingException e) {
		super(title,getMsg(dtoName, servico, url), e.getMessage(), 668, 500, e);
	}
		
	private static String getMsg(String dtoName, String servico, String url) {
		return String.format("Erro durante parseamento do contrato dto: %s vindo do servico: %s da url: %s",
							  dtoName, servico, url);
	}
	
	public String logErrorMsg() {
		return String.format("%s - %s", getMessage(), getDetailError());
	}

	
	
}
