package com.hoangtn.techbank.account.command.api.command;

public interface CommandHandler {
    void handle(OpenAccountCommand command);
    void handle(CloseAccountCommand command);
    void handle(DepositFundCommand command);
    void handle(WithdrawFundCommand command);
}
