package com.finflow.loan.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanRequest {
    private String accountNumber;
    private Double loanAmount;
    private Integer tenureMonths;
}
