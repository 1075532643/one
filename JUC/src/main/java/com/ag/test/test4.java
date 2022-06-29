package com.ag.test;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Slf4j(topic = "c.test4")
public class test4 {


    public static void main(String[] args) {
        Room room = new Room();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 3000; i++) {
               room.increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for(int k = 0;k<2000;k++){
                room.decrement();
            }
        }, "t2");

        t1.start();

        t2.start();
        try {
            t1.join();
            t2.join();
            log.debug("join开始");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.debug("{}",room.getCount());

    }

}
class Room{
    private int count=0;

    public void increment(){
        synchronized (this){
            count++;

        }
    }
    public void decrement(){
        synchronized (this){
            count--;
        }
    }

    public int getCount(){
        synchronized (this){
            return count;
        }
    }
}