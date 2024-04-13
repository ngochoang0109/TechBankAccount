package com.hoangtn.techbank.cqrs.core.handler;

import com.hoangtn.techbank.cqrs.core.domain.AggregateRoot;

public interface EventSourcingHandler<T> {
    void save(AggregateRoot aggregateRoot);
    T getById(String id);
}
