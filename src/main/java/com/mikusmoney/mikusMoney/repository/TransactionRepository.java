package com.mikusmoney.mikusMoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mikusmoney.mikusMoney.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    boolean existsByIdempotencyKey(String idempotencyKey);}