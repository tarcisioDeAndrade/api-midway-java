package com.api.adapter.out.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.adapter.out.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>{

}
