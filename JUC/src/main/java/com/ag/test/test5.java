package com.ag.test;

import lombok.extern.slf4j.Slf4j;
//两个线程谁都可能先执行。看cpu的调度
@Slf4j(topic = "test5")
public class test5 {
    public static void main(String[] args) {

        number number = new number();
        new Thread(()->{
            log.debug("1开始");
            number.a();
        },"1").start();
        new Thread(()->{
            log.debug("2开始");
            number.b();
        },"2").start();
    }
}
@Slf4j(topic = "num")
class number{
    public synchronized  void a(){
        log.debug("a");
    }

    public synchronized void b(){
        log.debug("b");
    }
}
