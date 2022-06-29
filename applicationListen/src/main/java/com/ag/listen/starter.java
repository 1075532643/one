package com.ag.listen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class starter {


    public static void main(String[] args) {
        /*ConfigurableApplicationContext run = SpringApplication.run(starter.class, args);
        EventDataDTO eventDataDTO = new EventDataDTO();
        MyEvent myEvent = new MyEvent(eventDataDTO);
        run.publishEvent(myEvent);*/


        //获取springApplication
        SpringApplication springApplication = new SpringApplication(starter.class);
       //添加监听器 如果没有加@component注解，那么就需要添加指定的监听器，否则方法上面的@eventListenr注解不起作用
       // springApplication.addListeners(new myListenOne());
        System.out.println("开始");
        //启动springBoot项目
        ApplicationContext run = springApplication.run(args);
        test(run);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("进入新的线程");
                //test(run);

            }
        }).start();


    }

    public static void test(ApplicationContext applicationContext){
        EventDataDTO eventDataDTO = new EventDataDTO();
        MyEvent myEvent = new MyEvent(eventDataDTO);
        System.out.println("test内部");
        applicationContext.publishEvent(myEvent);
    }
}
