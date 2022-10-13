package com.ag.JUC;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j(topic = "reentrantLock.test")
public class ReentrantLockReadTest {
    public static void main(String[] args) {
        DateContainer dateContainer = new DateContainer();
        new Thread(()->{
            dateContainer.read();

        },"t1").start();

        new Thread(()->{
            dateContainer.write();
        },"t2").start();
    }

}
@Slf4j
class DateContainer{

    private Object date;

    private ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
    //读锁  读-读可以并发， 读写是互斥的，一个线程获取了读锁，不能同时获得写锁，必须将读锁释放之后才能获取。
    private ReentrantReadWriteLock.ReadLock  r = rw.readLock();
    //写锁
    private  ReentrantReadWriteLock.WriteLock w = rw.writeLock();
    public Object read(){
        r.lock();
        log.debug("获取读锁....");
        try {
            log.debug("读锁....");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }finally {
            log.debug("读锁释放....");
            r.unlock();
        }
        return date;
    }

    public void write(){
        w.lock();
        log.debug("获取写锁....");
        try {
            log.debug("写锁....");
        }finally {
            log.debug("写锁释放....");
            w.unlock();
        }
    }
}