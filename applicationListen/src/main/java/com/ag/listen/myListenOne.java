package com.ag.listen;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class myListenOne implements ApplicationListener<MyEvent> {


   /*
   * 处理事件的逻辑
   * */
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("线程一"+ Thread.currentThread().getName()+ "】 => "
                + "监听器-【MyListenerOne】 => "
                + "监听到的事件-【" + myEvent + "】");
    }
}
