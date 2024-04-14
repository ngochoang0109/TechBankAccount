package com.hoangtn.techbank.cqrs.core.producer;

import com.hoangtn.techbank.cqrs.core.event.BaseEvent;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}
