package com.api.adapter.out.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "tb_transaction")
@Data
@AllArgsConstructor
public class Transaction {
	
	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;
	
    @Column(name = "transaction")
    private String transaction;

}
