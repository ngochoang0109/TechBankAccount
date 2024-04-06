package com.hoangtn.techbank.cqrs.core.event;

import com.hoangtn.techbank.cqrs.core.message.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class BaseEvent extends Message {
    private int version;
}
