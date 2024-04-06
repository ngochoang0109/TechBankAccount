package com.hoangtn.techbank.account.command.api.command;

import com.hoangtn.techbank.cqrs.core.command.BaseCommand;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class CloseAccountCommand extends BaseCommand {

    public CloseAccountCommand(String id) {
        super(id);
    }
}
