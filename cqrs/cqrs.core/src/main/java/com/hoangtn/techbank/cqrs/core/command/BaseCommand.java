package com.hoangtn.techbank.cqrs.core.command;

import com.hoangtn.techbank.cqrs.core.message.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
