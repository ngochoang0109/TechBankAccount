package com.hoangtn.techbank.account.command.domain;

import com.hoangtn.techbank.account.command.api.command.OpenAccountCommand;
import com.hoangtn.techbank.account.common.event.AccountClosedEvent;
import com.hoangtn.techbank.account.common.event.AccountOpenedEvent;
import com.hoangtn.techbank.account.common.event.FundDepositedEvent;
import com.hoangtn.techbank.account.common.event.FundWithDrawnEvent;
import com.hoangtn.techbank.cqrs.core.domain.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private boolean active;

    @Getter
    private double balance;

    public AccountAggregate(OpenAccountCommand openAccountCommand) {
        AccountOpenedEvent accountOpenedEvent = new AccountOpenedEvent();
        accountOpenedEvent.setAccountHolder(openAccountCommand.getAccountHolder());
        accountOpenedEvent.setAccountType(openAccountCommand.getAccountType());
        accountOpenedEvent.setCreatedDate(new Date());
        accountOpenedEvent.setOpeningBalance(openAccountCommand.getOpeningBalance());
        accountOpenedEvent.setId(openAccountCommand.getId());

        this.raiseEvent(accountOpenedEvent);
    }

    public void apply(AccountOpenedEvent event) {
        this.id = event.getId();
        this.active = true;
        this.balance = event.getOpeningBalance();
    }

    public void depositFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be deposit into a closed account!");
        }

        if (amount <= 0) {
            throw new IllegalStateException("The deposit amount must be greater than 0!");
        }

        FundDepositedEvent fundDepositedEvent = new FundDepositedEvent();
        fundDepositedEvent.setId(this.getId());
        fundDepositedEvent.setAmount(amount);
        raiseEvent(fundDepositedEvent);
    }

    public void apply(FundDepositedEvent event) {
        this.id = event.getId();
        this.balance = this.balance + event.getAmount();
    }

    public void withdrawnFunds(double amount) {
        if (!this.active) {
            throw new IllegalStateException("Funds cannot be withdrawn into a closed account!");
        }

        FundWithDrawnEvent fundWithDrawnEvent = new FundWithDrawnEvent();
        fundWithDrawnEvent.setId(this.getId());
        fundWithDrawnEvent.setAmount(amount);
        raiseEvent(fundWithDrawnEvent);
    }

    public void apply(FundWithDrawnEvent event) {
        this.id = event.getId();
        this.balance = this.balance - event.getAmount();
    }

    public void closeAccount() {
        if (!this.active) {
            throw new IllegalStateException("The bank account has already been closed!");
        }

        AccountClosedEvent accountClose = new AccountClosedEvent();
        accountClose.setId(this.getId());
        raiseEvent(accountClose);
    }

    public void apply(AccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }

}
