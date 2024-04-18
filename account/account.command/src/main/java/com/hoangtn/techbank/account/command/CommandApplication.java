package com.hoangtn.techbank.account.command;

import com.hoangtn.techbank.account.command.api.command.*;
import com.hoangtn.techbank.cqrs.core.infrastructure.CommandDispatcher;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CommandApplication {

	private final CommandDispatcher commandDispatcher;
	private final CommandHandler commandHandler;

	public CommandApplication(CommandDispatcher commandDispatcher,
							  CommandHandler commandHandler) {
		this.commandDispatcher = commandDispatcher;
		this.commandHandler = commandHandler;
	}

	public static void main(String[] args) {
		SpringApplication.run(CommandApplication.class, args);
	}

	@PostConstruct
	public void registerHandlers() {
		commandDispatcher.registerHandler(OpenAccountCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(DepositFundCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(WithdrawFundCommand.class, commandHandler::handle);
		commandDispatcher.registerHandler(CloseAccountCommand.class, commandHandler::handle);
	}
}
