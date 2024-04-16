package com.hoangtn.techbank.account.query.infrastructure.consumer;

import com.hoangtn.techbank.account.common.event.AccountClosedEvent;
import com.hoangtn.techbank.account.common.event.AccountOpenedEvent;
import com.hoangtn.techbank.account.common.event.FundDepositedEvent;
import com.hoangtn.techbank.account.common.event.FundWithDrawEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer {
    void consumer(@Payload AccountOpenedEvent event, Acknowledgment acknowledgeMode);
    void consumer(@Payload FundDepositedEvent event, Acknowledgment acknowledgeMode);
    void consumer(@Payload FundWithDrawEvent event, Acknowledgment acknowledgeMode);
    void consumer(@Payload AccountClosedEvent event, Acknowledgment acknowledgeMode);
}
