package com.api.adapter.in.http.transaction.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDTO {
	private String name;
	private Double valor;
}
