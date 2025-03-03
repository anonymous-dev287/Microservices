package com.finflow.account.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finflow.account.dto.AccountDTO;
import com.finflow.account.entity.Account;
import com.finflow.account.exception.AccountNotFoundException;
import com.finflow.account.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
public class AccountService {
	
	@Autowired
    private AccountRepository accountRepository;


    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(account -> new AccountDTO(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance()))
                .collect(Collectors.toList());
    }

    public AccountDTO getAccountByNumber(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        return new AccountDTO(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
    }

    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = Account.builder()
                .accountNumber(accountDTO.getAccountNumber())
                .accountHolderName(accountDTO.getAccountHolderName())
                .balance(accountDTO.getBalance())
                .build();
        account = accountRepository.save(account);
        return new AccountDTO(account.getAccountNumber(), account.getAccountHolderName(), account.getBalance());
    }

    public void deleteAccount(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + accountNumber));
        accountRepository.delete(account);
    }
}
