package com.hoangtn.techbank.account.query.infrastructure.handler;

import com.hoangtn.techbank.account.common.event.AccountClosedEvent;
import com.hoangtn.techbank.account.common.event.AccountOpenedEvent;
import com.hoangtn.techbank.account.common.event.FundDepositedEvent;
import com.hoangtn.techbank.account.common.event.FundWithDrawEvent;
import com.hoangtn.techbank.account.query.domain.AccountRepository;
import com.hoangtn.techbank.account.query.domain.BankAccount;

public class AccountEventHandler implements EventHandler {

    private final AccountRepository accountRepository;

    public AccountEventHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void on(AccountOpenedEvent event) {
        BankAccount account = new BankAccount();
        account.setId(event.getId());
        account.setAccountHolder(event.getAccountHolder());
        account.setCreationDate(event.getCreatedDate());
        account.setAccountType(event.getAccountType());
        account.setBalance(event.getOpeningBalance());
        accountRepository.save(account);
    }

    @Override
    public void on(FundDepositedEvent event) {
        BankAccount account = accountRepository.findById(event.getId()).orElseThrow(
            () -> new RuntimeException()
        );

        double currentBalance = account.getBalance();
        double latestBalance = currentBalance + event.getAmount();
        account.setBalance(latestBalance);
        accountRepository.save(account);
    }

    @Override
    public void on(FundWithDrawEvent event) {
        BankAccount account = accountRepository.findById(event.getId()).orElseThrow(
            () -> new RuntimeException()
        );

        double currentBalance = account.getBalance();
        double latestBalance = currentBalance - event.getAmount();
        account.setBalance(latestBalance);
        accountRepository.save(account);
    }

    @Override
    public void on(AccountClosedEvent event) {
        accountRepository.deleteById(event.getId());
    }

}
