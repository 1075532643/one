package com.ag.listen;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class MyEvent extends ApplicationEvent {

    @Getter
    private final EventDataDTO eventDataDTO;

    public MyEvent(EventDataDTO source) {
        super(source);
        System.out.println("进入MyEvent构造器内部");
        this.eventDataDTO = source;
    }
}
