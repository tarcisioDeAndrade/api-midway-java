package com.api.adapter.in.http.mapper;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.adapter.in.http.exception.InvalidContractX9Exception;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ContractParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ContractParser.class);
	private static final String EMPTY = StringUtils.EMPTY;
	
	private ObjectMapper mapper;
	
	public ContractParser() {
		this(new ObjectMapper());
	}
	
	protected ContractParser(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	public <T, U> T parseResponse(String content, Class<T> clazz, Class<U> servico, String url) {
		return parseResponse(content, clazz, servico.getName(), url);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T parseResponse(String content, Class<T> clazz, String servicoNome, String url) {
		if(clazz == String.class) {
			return (T) content;
		}
		
		if(StringUtils.isAllBlank(content)) {
			InvalidContractX9Exception e = new InvalidContractX9Exception(clazz.getName(), servicoNome, url, "Erro string vazia enviada para ser parseada em objeto");
			LOGGER.error(e.logErrorMsg());
			throw e;
		}
		
		try {
			return mapper.readValue(content, clazz);
		} catch (JsonProcessingException e) {
			InvalidContractX9Exception e1 = tratamentoParaInvalidContractException(clazz.getName(), servicoNome, url, e);
			throw e1;
		}
	}
	
	public <T, U> T parseString(String content, Class<T> clazz, Class<U> servico) {
		return parseResponse(content, clazz, servico, EMPTY);
	}
	
	@SuppressWarnings("unchecked")
	public <T,U> T parseStringOrNull(String content, Class<T> clazz, Class<U> servico) {
		if(clazz == String.class) {
			return (T) content;
		}
		
		if(StringUtils.isAllBlank(content)) {
			return null;
		}
		
		try {
			return mapper.readValue(content, clazz);
		} catch(JsonProcessingException e ) {
			return null;
		}
	}
	
	public <T, U> String parseRequest(T obj, Class<U> servico) {
		if(obj instanceof String) {
			return (String) obj;
		}
		
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			InvalidContractX9Exception e1 = tratamentoParaInvalidContractException(obj.getClass().getName(), servico.getName(),"", e);
			throw e1;
		}
	}
	
	public <T, U> String parseObj(T obj, Class<U> servico) {
		return parseRequest(obj, servico);
	}
	
	public <T, U> T parseMap(Map<String, String> map, Class<T> clazz, Class<U> servico, String url) {
		String value = parseRequest(map, servico);
		return parseResponse(value,clazz,servico,url);
	}
	
	private InvalidContractX9Exception tratamentoParaInvalidContractException(String nomeDTO, String service, String url, JsonProcessingException e) {
		InvalidContractX9Exception e1 = new InvalidContractX9Exception(nomeDTO, service, url, e);
		LOGGER.error(e1.logErrorMsg());
		return e1;
	}
	

}
