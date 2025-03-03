package com.finflow.account.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String accountNumber;
    private String accountHolderName;
    private Double balance;
}
