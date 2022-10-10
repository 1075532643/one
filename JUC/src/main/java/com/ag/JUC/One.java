package com.ag.JUC;

import net.bytebuddy.agent.builder.AgentBuilder;

import java.security.PrivateKey;
import java.util.concurrent.atomic.AtomicInteger;

public class One {

    public static void main(String[] args) {


        waitNotify waitNotify = new waitNotify(1,5);
        new Thread(()->{waitNotify.print("a",1,2);},"线程1").start();
        new Thread(()->{waitNotify.print("b",2,3);},"线程1").start();
        new Thread(()->{waitNotify.print("c",3,1);},"线程1").start();


    }

}
class waitNotify{
    private int loopNumber;
    private int flag;

    public waitNotify(int flag,int loopNumber){
        this.flag=flag;
        this.loopNumber=loopNumber;
    }


    public void print(String str,int waitFlag,int nextFlag){
        for (int i =0;i<loopNumber;i++){
            synchronized (this){
                if(flag != waitFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(str);
                waitFlag=nextFlag;
                this.notifyAll();
            }
        }

    }
}