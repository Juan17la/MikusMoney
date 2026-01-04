package com.mikusmoney.mikusMoney.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mikusmoney.mikusMoney.dto.transactionsDTOs.WithdrawResponse;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.depositDTOs.DepositRequest;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.depositDTOs.DepositResponse;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.sendDTOs.SendMoneyRequest;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.sendDTOs.SendResponse;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.TransactionHistoryResponse;
import com.mikusmoney.mikusMoney.dto.transactionsDTOs.WithdrawRequest;
import com.mikusmoney.mikusMoney.entity.Miku;
import com.mikusmoney.mikusMoney.entity.Transaction;
import com.mikusmoney.mikusMoney.mapper.history.TransactionHistoryMapperRegistry;
import com.mikusmoney.mikusMoney.repository.TransactionRepository;
import com.mikusmoney.mikusMoney.services.operations.DepositOperation;
import com.mikusmoney.mikusMoney.services.operations.SendOperation;
import com.mikusmoney.mikusMoney.services.operations.WithdrawOperation;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionRepository transactionRepository;
    private final TransactionHistoryMapperRegistry historyMapperRegistry;

    private final DepositOperation depositOperation;
    private final WithdrawOperation withdrawOperation;
    private final SendOperation sendOperation;
    
    private final AuthContextService authContextService;


    @Transactional
    public DepositResponse deposit(DepositRequest rq, String key) {
        return depositOperation.execute(rq, key);
    }

    @Transactional
    public WithdrawResponse withdraw(WithdrawRequest request, String idempotencyKey) {
        return withdrawOperation.execute(request, idempotencyKey);
    }

    @Transactional
    public SendResponse send(SendMoneyRequest request, String idempotencyKey) {
       return sendOperation.execute(request, idempotencyKey);
    }

    @Transactional
    public Page<TransactionHistoryResponse> getTransactionHistory(int page) {
        Miku miku = authContextService.getAuthenticatedMiku();
        
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        
        Page<Transaction> transactions = transactionRepository.findAllByMikuId(miku.getId(), pageable);
        
        return transactions.map(historyMapperRegistry::toHistoryResponse);
    }

}