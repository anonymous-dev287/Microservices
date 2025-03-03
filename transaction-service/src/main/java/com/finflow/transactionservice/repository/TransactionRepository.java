package com.finflow.transactionservice.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.finflow.transactionservice.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySenderAccount(String senderAccount);
    List<Transaction> findByReceiverAccount(String receiverAccount);
}

