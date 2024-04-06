package com.hoangtn.techbank.account.command.infrastructure;

import com.hoangtn.techbank.cqrs.core.command.BaseCommand;
import com.hoangtn.techbank.cqrs.core.command.CommandHandlerMethod;
import com.hoangtn.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        List<CommandHandlerMethod> handlers = routes.computeIfAbsent(type, route -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        List<CommandHandlerMethod> handlers = routes.get(command.getClass());

        if (handlers == null || handlers.isEmpty()) {
            throw new RuntimeException("No command handler was registered!");
        }

        if (handlers.size() > 1){
            throw new RuntimeException("Can't send command to more than one handler!");
        }

        handlers.get(0).handle(command);
    }

}
