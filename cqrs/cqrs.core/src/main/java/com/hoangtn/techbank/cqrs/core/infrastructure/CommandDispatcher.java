package com.hoangtn.techbank.cqrs.core.infrastructure;

import com.hoangtn.techbank.cqrs.core.command.BaseCommand;
import com.hoangtn.techbank.cqrs.core.command.CommandHandlerMethod;

/**
 * Mediator interface
 */
public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
