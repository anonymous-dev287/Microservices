package com.finflow.transactionservice.service;



import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.finflow.transactionservice.dto.TransactionRequest;
import com.finflow.transactionservice.dto.TransactionResponse;
import com.finflow.transactionservice.model.Transaction;
import com.finflow.transactionservice.repository.TransactionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionResponse makeTransaction(TransactionRequest request) {
        Transaction transaction = Transaction.builder()
                .senderAccount(request.getSenderAccount())
                .receiverAccount(request.getReceiverAccount())
                .amount(request.getAmount())
                .transactionDate(new Date())
                .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .senderAccount(transaction.getSenderAccount())
                .receiverAccount(transaction.getReceiverAccount())
                .amount(transaction.getAmount())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

    public List<TransactionResponse> getTransactionsByAccount(String accountNumber) {
        List<Transaction> transactions = transactionRepository.findBySenderAccount(accountNumber);
        transactions.addAll(transactionRepository.findByReceiverAccount(accountNumber));

        return transactions.stream()
                .map(transaction -> new TransactionResponse(
                        transaction.getSenderAccount(),
                        transaction.getReceiverAccount(),
                        transaction.getAmount(),
                        transaction.getTransactionDate()))
                .collect(Collectors.toList());
    }
}
