package com.mikusmoney.mikusMoney.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mikusmoney.mikusMoney.entity.Withdraw;

@Repository
public interface WithdrawRepository extends JpaRepository<Withdraw, Long> {}