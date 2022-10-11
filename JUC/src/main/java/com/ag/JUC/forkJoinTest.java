package com.ag.JUC;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

//适用于cpu密集型，递归相关，排序，斐波那契数列
@Slf4j(topic = "forkTest")
public class forkJoinTest {
    //1。任务对象
    //2。执行任务对象
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        System.out.println(forkJoinPool.invoke(new  MyTask(5)));
    }
}
//1-n之间整数的合
//本质和递归相同
@Slf4j
class MyTask extends RecursiveTask<Integer>{

    private int n;

    public MyTask(int n){
        this.n=n;
    }
    @Override
    protected Integer compute() {
        if(n==1){
            log.debug("join() {} ",n);
                return 1;
        }
        MyTask t1 = new MyTask(n - 1);
        t1.fork();//让一个线程去执行这个结果
        log.debug("fork() {} + {}",n,t1);

        int result = t1.join() + n;
        log.debug("join(){} + {} + {}",n,t1,result);

        return result;
    }
}
