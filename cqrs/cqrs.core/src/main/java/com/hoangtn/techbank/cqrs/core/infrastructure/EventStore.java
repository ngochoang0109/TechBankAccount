package com.hoangtn.techbank.cqrs.core.infrastructure;

import com.hoangtn.techbank.cqrs.core.event.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(String aggregateId);
}
