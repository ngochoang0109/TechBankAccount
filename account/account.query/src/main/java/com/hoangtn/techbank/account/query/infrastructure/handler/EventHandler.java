package com.hoangtn.techbank.account.query.infrastructure.handler;

import com.hoangtn.techbank.account.common.event.AccountClosedEvent;
import com.hoangtn.techbank.account.common.event.AccountOpenedEvent;
import com.hoangtn.techbank.account.common.event.FundDepositedEvent;
import com.hoangtn.techbank.account.common.event.FundWithDrawEvent;

public interface EventHandler {
    void on(AccountOpenedEvent event);
    void on(FundDepositedEvent event);
    void on(FundWithDrawEvent event);
    void on(AccountClosedEvent event);
}
