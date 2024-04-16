package com.hoangtn.techbank.account.query.infrastructure.consumer;

import com.hoangtn.techbank.account.common.event.AccountClosedEvent;
import com.hoangtn.techbank.account.common.event.AccountOpenedEvent;
import com.hoangtn.techbank.account.common.event.FundDepositedEvent;
import com.hoangtn.techbank.account.common.event.FundWithDrawEvent;
import com.hoangtn.techbank.account.query.infrastructure.handler.EventHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class AccountEventConsumer implements EventConsumer{
    private final EventHandler eventHandler;

    public AccountEventConsumer(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @KafkaListener(topics = "AccountOpenedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(AccountOpenedEvent event, Acknowledgment acknowledgeMode) {
        eventHandler.on(event);
        // Commit to receive latest message from message in the feature
        acknowledgeMode.acknowledge();
    }

    @KafkaListener(topics = "FundDepositedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(FundDepositedEvent event, Acknowledgment acknowledgeMode) {
        eventHandler.on(event);
        // Commit to receive latest message from message in the feature
        acknowledgeMode.acknowledge();
    }

    @KafkaListener(topics = "FundWithDrawEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(FundWithDrawEvent event, Acknowledgment acknowledgeMode) {
        eventHandler.on(event);
        // Commit to receive latest message from message in the feature
        acknowledgeMode.acknowledge();
    }

    @KafkaListener(topics = "AccountClosedEvent", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void consumer(AccountClosedEvent event, Acknowledgment acknowledgeMode) {
        eventHandler.on(event);
        // Commit to receive latest message from message in the feature
        acknowledgeMode.acknowledge();
    }

}
