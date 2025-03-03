package com.finflow.loan.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private Double loanAmount;

    @Column(nullable = false)
    private Double interestRate;  // Interest Rate (in %)

    @Column(nullable = false)
    private Integer tenureMonths; // Loan Tenure in Months

    @Column(nullable = false)
    private String status;  // "PENDING", "APPROVED", "REJECTED", "REPAID"

    @Temporal(TemporalType.TIMESTAMP)
    private Date appliedDate;
}
