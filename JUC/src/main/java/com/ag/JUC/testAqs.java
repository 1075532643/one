package com.ag.JUC;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@Slf4j(topic = "c.aqs")
public class testAqs {
    public static void main(String[] args) {
        MyLock myLock = new MyLock();
        new Thread(() -> {
            myLock.lock();
            try {
                log.debug("locking...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                log.debug("unlocking...");
                myLock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            myLock.lock();
            try {
                log.debug("locking...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } finally {
                log.debug("unlocking...");
                myLock.unlock();
            }
        }, "t2").start();
    }
}
//自定义锁，（不可重入）,一个线程只能加一次同一种锁
class MyLock implements Lock{

    private MySync mySync =new MySync();
    //同步器类
    class MySync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(0,1)){
                //加上了锁，owner为当前线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            setExclusiveOwnerThread(null);
            //state是volaite的，字段前面的指令不会重排序。保持可见
            setState(0);
            return true;
        }

        //是否持有独占锁
        @Override
        protected boolean isHeldExclusively() {
            return super.isHeldExclusively();
        }

        private  Condition newCondition(){
            return new ConditionObject();
        }
    }


    @Override
    public void lock() {
        mySync.acquire(1);
    }

    //可打断的加锁
    @Override
    public void lockInterruptibly() throws InterruptedException {
        mySync.acquireInterruptibly(1);
    }

    //尝试加锁一次
    @Override
    public boolean tryLock() {
        return mySync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return mySync.tryAcquireNanos(1,unit.toNanos(time));
    }

    //release方法会将当前线程持有的锁改为null，并唤醒队列中阻塞的线程。而tryRelease不会唤醒阻塞的队列
    @Override
    public void unlock() {
        mySync.release(1);
    }

    @Override
    public Condition newCondition() {
        return mySync.newCondition();
    }
}
