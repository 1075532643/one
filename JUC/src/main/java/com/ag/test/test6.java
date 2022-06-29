package com.ag.test;

import lombok.extern.slf4j.Slf4j;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

@Slf4j(topic = "test7")
public class test6 {
    public static void main(String[] args) throws InterruptedException {
        TicketWindow ticketWindow = new TicketWindow(10000);
        //所有线程的集合
        List<Thread> threads = new ArrayList<>();
        //卖出的票数统计
        List<Integer> amountList  = new Vector<>();
        for(int i = 0;i<2000;i++){
            Thread t = new Thread(() -> {
                //买票
                int amount = ticketWindow.sell(randomAmount());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                amountList.add(amount);
            });
            threads.add(t);
            t.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        //统计卖出的票数和剩余票数
        log.debug("余票：{}",ticketWindow.getCount());
        log.debug("卖出的票数，{}",amountList.stream().mapToInt(i->i).sum());
    }
    static Random random = new Random();
    public static int randomAmount(){
        return random.nextInt(5)+1;
    }
}
class TicketWindow{
    private int count;

    public TicketWindow(int count){
        this.count = count;
    }
    public int getCount(){
        return count;
    }
    public synchronized int sell(int count){
        if(this.count>= count){
            this.count -=count;
            return count;
        }else {
            return 0;
        }
    }
}