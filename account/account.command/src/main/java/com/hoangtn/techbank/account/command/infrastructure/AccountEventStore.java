package com.hoangtn.techbank.account.command.infrastructure;

import com.hoangtn.techbank.account.command.domain.AccountAggregate;
import com.hoangtn.techbank.account.command.domain.EventStoreRepository;
import com.hoangtn.techbank.cqrs.core.event.BaseEvent;
import com.hoangtn.techbank.cqrs.core.event.EventModel;
import com.hoangtn.techbank.cqrs.core.exception.AggregateNotFoundException;
import com.hoangtn.techbank.cqrs.core.exception.ConcurrencyException;
import com.hoangtn.techbank.cqrs.core.infrastructure.EventStore;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;

    public AccountEventStore(EventStoreRepository eventStoreRepository){
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    public void saveEvent(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (expectedVersion != -1 && eventStream.get(0).getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }

        int verion = expectedVersion;
        for (BaseEvent event : events) {
            verion++;
            EventModel eventModel = new EventModel();
            eventModel.setTimeStamp(new Date());
            eventModel.setAggregateIdentifier(aggregateId);
            eventModel.setAggregateType(AccountAggregate.class.getName());
            eventModel.setVersion(verion);
            eventModel.setEventType(event.getClass().getTypeName());
            eventModel.setEventData(event);

            EventModel persistedEventModel = eventStoreRepository.save(eventModel);
            if (persistedEventModel != null) {
                // TODO: produce event to kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        List<EventModel> eventStream = eventStoreRepository.findByAggregateIdentifier(aggregateId);
        if (eventStream == null || eventStream.isEmpty()) {
            throw new AggregateNotFoundException("Incorrect account id provided!");
        }
        return eventStream.stream().map(EventModel::getEventData).toList();
    }
}
