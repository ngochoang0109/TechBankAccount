package com.hoangtn.techbank.cqrs.core.domain;

import com.hoangtn.techbank.cqrs.core.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AggregateRoot {

    @Getter
    protected String id;

    @Getter
    @Setter
    protected int version = -1;

    /**
     * Contain all changes that make to Aggregate in the form of events
     */
    private final List<BaseEvent> changes = new ArrayList<>();

    private final Logger logger = Logger.getLogger(AggregateRoot.class.getName());

    public List<BaseEvent> getUncommittedChanges() {
        return this.changes;
    }

    public void markChangesIsCommitted() {
        this.changes.clear();
    }

    protected void applyChange(BaseEvent event, boolean isNewEvent) {
        try {
            Method method = getClass().getDeclaredMethod("apply", event.getClass());
            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            logger.log(Level.WARNING, MessageFormat.format("The apply method was not found in the aggregate for {0}", event.getClass().getName()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error applying event to aggregate", e);
        } finally {
            if (isNewEvent) {
                changes.add(event);
            }
        }
    }

    public void raiseEvent(BaseEvent event) {
        this.applyChange(event, Boolean.TRUE);
    }

    public void replayEvents(Iterable<BaseEvent> events) {
        events.forEach(event -> {
            applyChange(event, Boolean.FALSE);
        });
    }
}
