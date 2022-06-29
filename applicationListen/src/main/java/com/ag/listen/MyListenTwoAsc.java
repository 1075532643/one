package com.ag.listen;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


public class MyListenTwoAsc implements ApplicationListener<MyEvent> {

    @Override
    @Async
    @EventListener
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("这是异步方法的监听器");
    }
}
