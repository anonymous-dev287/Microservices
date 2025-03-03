package com.finflow.loan.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {
    private Long id;
    private String accountNumber;
    private Double loanAmount;
    private Double interestRate;
    private Integer tenureMonths;
    private String status;
    private Date appliedDate;
}
