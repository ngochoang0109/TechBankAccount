package com.hoangtn.techbank.account.common.event;

import com.hoangtn.techbank.account.common.dto.AccountType;
import com.hoangtn.techbank.cqrs.core.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
public class AccountOpenedEvent extends BaseEvent {

    private String accountHolder;
    private AccountType accountType;
    private Date createdDate;
    private double openingBalance;

}
