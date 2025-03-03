package com.finflow.transactionservice.controller;

import java.util.List;

import org.apache.hc.core5.http.HttpStatus;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finflow.transactionservice.dto.TransactionRequest;
import com.finflow.transactionservice.dto.TransactionResponse;
import com.finflow.transactionservice.service.TransactionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {
	
    private final TransactionService transactionService;
    
   

    @PostMapping("/create/v1")
    public ResponseEntity<TransactionResponse> makeTransaction(@RequestBody TransactionRequest request) {
        return ResponseEntity.ok(transactionService.makeTransaction(request));
    }
    @PostMapping("/create")
    public ResponseEntity<String> createTransaction(@RequestHeader HttpHeaders headers) {
        log.info("Incoming request headers: {}", headers);

        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            log.error("Authentication failed! SecurityContext is empty.");
            return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Unauthorized: SecurityContext is null");
        }

        log.info("{Transaction-srvice} Authenticated user: {}", authentication.getName());
        return ResponseEntity.ok("Transaction Created");
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactions(@PathVariable String accountNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsByAccount(accountNumber));
    }
}

