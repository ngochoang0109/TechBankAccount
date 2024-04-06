package com.hoangtn.techbank.account.command.api.command;

import com.hoangtn.techbank.account.common.dto.AccountType;
import com.hoangtn.techbank.cqrs.core.command.BaseCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class OpenAccountCommand extends BaseCommand {

    private String accountHolder;
    private AccountType accountType;
    private double openingBalance;

    public OpenAccountCommand(String id) {
        super(id);
    }
}
