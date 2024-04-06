package com.hoangtn.techbank.account.common.event;

import com.hoangtn.techbank.cqrs.core.event.BaseEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class AccountClosedEvent extends BaseEvent {
}
