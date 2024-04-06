package com.hoangtn.techbank.account.command.domain;

import com.hoangtn.techbank.account.command.api.command.OpenAccountCommand;
import com.hoangtn.techbank.cqrs.core.domain.AggregateRoot;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private boolean active;
    private double balance;

    public AccountAggregate(OpenAccountCommand openAccountCommand) {

    }
}
