package com.finflow.loan.service;

import com.finflow.loan.dto.LoanRequest;
import com.finflow.loan.dto.LoanResponse;
import com.finflow.loan.model.Loan;
import com.finflow.loan.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanResponse applyForLoan(LoanRequest request) {
        // Dummy interest calculation (could be dynamic based on loan amount & tenure)
        double interestRate = 5.5; 

        Loan loan = Loan.builder()
                .accountNumber(request.getAccountNumber())
                .loanAmount(request.getLoanAmount())
                .interestRate(interestRate)
                .tenureMonths(request.getTenureMonths())
                .status("PENDING")  // Initially, loans are pending approval
                .appliedDate(new Date())
                .build();

        loanRepository.save(loan);

        return LoanResponse.builder()
                .id(loan.getId())
                .accountNumber(loan.getAccountNumber())
                .loanAmount(loan.getLoanAmount())
                .interestRate(loan.getInterestRate())
                .tenureMonths(loan.getTenureMonths())
                .status(loan.getStatus())
                .appliedDate(loan.getAppliedDate())
                .build();
    }

    public LoanResponse approveLoan(Long loanId) {
        Optional<Loan> loanOptional = loanRepository.findById(loanId);
        if (loanOptional.isPresent()) {
            Loan loan = loanOptional.get();
            loan.setStatus("APPROVED");
            loanRepository.save(loan);
            return LoanResponse.builder()
                    .id(loan.getId())
                    .accountNumber(loan.getAccountNumber())
                    .loanAmount(loan.getLoanAmount())
                    .interestRate(loan.getInterestRate())
                    .tenureMonths(loan.getTenureMonths())
                    .status(loan.getStatus())
                    .appliedDate(loan.getAppliedDate())
                    .build();
        }
        throw new RuntimeException("Loan Not Found");
    }

    public List<LoanResponse> getLoansByAccount(String accountNumber) {
        return loanRepository.findByAccountNumber(accountNumber).stream()
                .map(loan -> LoanResponse.builder()
                        .id(loan.getId())
                        .accountNumber(loan.getAccountNumber())
                        .loanAmount(loan.getLoanAmount())
                        .interestRate(loan.getInterestRate())
                        .tenureMonths(loan.getTenureMonths())
                        .status(loan.getStatus())
                        .appliedDate(loan.getAppliedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
