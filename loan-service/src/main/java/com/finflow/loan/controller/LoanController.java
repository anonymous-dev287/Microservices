package com.finflow.loan.controller;

import com.finflow.loan.dto.LoanRequest;
import com.finflow.loan.dto.LoanResponse;
import com.finflow.loan.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<LoanResponse> applyForLoan(@RequestBody LoanRequest request) {
        return ResponseEntity.ok(loanService.applyForLoan(request));
    }

    @PostMapping("/approve/{loanId}")
    public ResponseEntity<LoanResponse> approveLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.approveLoan(loanId));
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<LoanResponse>> getLoans(@PathVariable String accountNumber) {
        return ResponseEntity.ok(loanService.getLoansByAccount(accountNumber));
    }
}
