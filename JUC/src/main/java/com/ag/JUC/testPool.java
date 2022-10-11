package com.ag.JUC;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.select.KSQLWindow;

import java.security.PrivateKey;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.test")
public class testPool {
    public static void main(String[] args) {

        threadPool threadPool = new threadPool(2, 1000, TimeUnit.MILLISECONDS, 10, (queue, task)->{
           //死等
            queue.put(task);

        });

        for(int i =0;i<15;i++){
            int j =i;
            threadPool.execute(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("{}",j);
            });
        }
    }
}
@Slf4j(topic = "c.treadPool")
class threadPool{
    //任务队列
    private BlockQueue<Runnable> taskQueue;
    //线程集合，存放线程（把线程包装成worker类）
    private HashSet<worker> works = new HashSet<>();
    //核心线程数
    private int coreSize;
    //超时时间
    private TimeUnit timeUnit;

    private Long timeout;

    private RejectPolicy<Runnable> rejectPolicy;
    public threadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity,RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockQueue<>(queueCapacity);
        this.rejectPolicy=rejectPolicy;
    }

    //执行任务
    class worker extends Thread {
        private Runnable task;

        public worker(Runnable task) {
            this.task = task;
        }

        @Override
        public void run() {
            //当task不为空，直接执行（在创建对象的时候传了一个任务，当这个任务执行结束之后再判断）
            //当task执行完毕，到任务队列获取任务并执行
            while (task != null || (task = taskQueue.poll(timeout,timeUnit)) != null) {
                try {
                    log.debug("正在执行。。。{}",task);
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    task = null;
                }
            }
            synchronized (works){
                log.debug("移除{}",works);
                works.remove(task);
            }
        }
    }


    public void execute(Runnable runnable) {
        //当任务数没有超过coreSize时，交给work对象执行
        if (works.size() < coreSize) {
            worker worker = new worker(runnable);
            log.debug("新增{}",worker);
            works.add(worker);
            worker.start();

            //任务数超过coreSize时，加入任务队列暂存
        } else {
           //死等 taskQueue.put(runnable);
            //有拒绝策略
            taskQueue.tryPut(rejectPolicy,runnable);
        }
    }

}

@Data
@Slf4j
class BlockQueue<T> {
    //1.任务队列
    private Deque<T> queue = new ArrayDeque<T>();

    //2.锁
    private ReentrantLock lock = new ReentrantLock();

    //3.生产者条件变量
    private Condition fullWaitSet = lock.newCondition();
    //消费者条件变量
    private Condition emptyWaitSet = lock.newCondition();

    //5.容量
    private int capacity;

    public BlockQueue(int capacity) {
        this.capacity = capacity;
    }

    public Deque<T> getQueue() {
        return queue;
    }

    public void setQueue(Deque<T> queue) {
        this.queue = queue;
    }

    //6.阻塞获取
    public T take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                try {
                    emptyWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //获取完之后要删除
            T t = queue.removeFirst();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //7.带超时的阻塞获取
    public T poll(Long timeout, TimeUnit timeUnit) {

        lock.lock();
        try {
            //将timeout转换为统一的纳秒
            long nanos = timeUnit.toNanos(timeout);
            while (queue.isEmpty()) {
                try {
                    if (nanos < 0) {
                        return null;
                    }
                    //返回的是剩余的时间
                    nanos = emptyWaitSet.awaitNanos(nanos);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            //获取完之后要删除
            T t = queue.removeFirst();
            return t;
        } finally {
            lock.unlock();
        }
    }

    //8.阻塞添加
    public void put(T t) {
        lock.lock();
        try {
            while (queue.size() == capacity) {
                try {
                    log.debug("等待加入任务队列{}...",t);
                    fullWaitSet.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.debug("新增work队列{}",t);
            queue.addLast(t);
            //唤醒等待的线程
            emptyWaitSet.signal();
        } finally {
            lock.unlock();
        }
    }

    //9.获取大小
    public int getCapacity() {
        lock.lock();
        try {
            return queue.size();
        } finally {
            lock.unlock();
        }
    }

    public void tryPut(RejectPolicy<T> rejectPolicy, T runnable) {
        lock.lock();
        try {
            //判断队列是否已经满了
            if(queue.size()==capacity){
                //策略
                rejectPolicy.reject(this,runnable);
            }else {

            }
        }finally {
            lock.unlock();
        }
    }
}
//决绝策略
interface RejectPolicy<T>{
    void reject(BlockQueue queue,T task);
}