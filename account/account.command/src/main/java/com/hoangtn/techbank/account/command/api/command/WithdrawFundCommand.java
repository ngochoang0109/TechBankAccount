package com.hoangtn.techbank.account.command.api.command;

import com.hoangtn.techbank.cqrs.core.command.BaseCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class WithdrawFundCommand extends BaseCommand {

    private double amount;

    public WithdrawFundCommand(String id) {
        super(id);
    }
}
