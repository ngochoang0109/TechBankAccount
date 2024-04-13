package com.hoangtn.techbank.account.command.infrastructure;

import com.hoangtn.techbank.account.command.domain.AccountAggregate;
import com.hoangtn.techbank.cqrs.core.domain.AggregateRoot;
import com.hoangtn.techbank.cqrs.core.event.BaseEvent;
import com.hoangtn.techbank.cqrs.core.handler.EventSourcingHandler;
import com.hoangtn.techbank.cqrs.core.infrastructure.EventStore;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AccountEventSourcingHandler implements EventSourcingHandler<AccountAggregate> {

    private final EventStore eventStore;

    public AccountEventSourcingHandler(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @Override
    public void save(AggregateRoot aggregateRoot) {
        eventStore.saveEvent(aggregateRoot.getId(), aggregateRoot.getUncommittedChanges(), aggregateRoot.getVersion());
        aggregateRoot.markChangesIsCommitted();
    }

    @Override
    public AccountAggregate getById(String id) {
        AccountAggregate accountAggregate = new AccountAggregate();
        List<BaseEvent> events = eventStore.getEvents(id);
        if (events != null && !events.isEmpty()) {
            accountAggregate.replayEvents(events);
            int latestVersion = events.stream().map(e -> e.getVersion()).max(Comparator.naturalOrder()).orElseThrow(
                () -> new RuntimeException()
            );
            accountAggregate.setVersion(latestVersion);
        }
        return accountAggregate;
    }
}
