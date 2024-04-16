package com.hoangtn.techbank.account.command.api.controller;

import com.hoangtn.techbank.account.command.api.command.OpenAccountCommand;
import com.hoangtn.techbank.account.command.api.payload.OpenAccountResponse;
import com.hoangtn.techbank.account.common.dto.BaseResponse;
import com.hoangtn.techbank.cqrs.core.infrastructure.CommandDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/openBankAccount")
public class OpenAccountController {
    private final Logger logger = Logger.getLogger(OpenAccountController.class.getName());

    private final CommandDispatcher commandDispatcher;

    public OpenAccountController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping(value = "")
    public ResponseEntity<BaseResponse> openAccount(@RequestBody OpenAccountCommand account) {
        String id = UUID.randomUUID().toString();
        account.setId(id);
        try {
            commandDispatcher.send(account);
            return new ResponseEntity<>(new OpenAccountResponse(id, "Bank account creation request completed successfully"), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Client made a bad request - {0}!", e.toString()));
            return new ResponseEntity<>(new BaseResponse(e.toString()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            String messageError = MessageFormat.format("Error while processing request to open a new bank account for id - {0}", id);
            logger.log(Level.SEVERE, messageError, e);
            return new ResponseEntity<>(new OpenAccountResponse(id, messageError), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
