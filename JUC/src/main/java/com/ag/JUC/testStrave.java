package com.ag.JUC;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j(topic = "c.testStrave")
public class testStrave {
    static final List<String> list = Arrays.asList("");
    static Random random = new Random();
    static  String cooking(){
        return list.get(random.nextInt(list.size()));
    }
    public void method1() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //两个线程同时被占用，且都在等待菜烧完。此时没有多余的线程处理其他的
        //下面解决方法
        executorService.execute(()->{
            log.debug("处理点餐");
            Future<String> f = executorService.submit(() -> {
                log.debug("烧草");
                return cooking();
            });
            try {
                log.debug("上菜{}",f.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        executorService.execute(()->{
            log.debug("处理点餐");
            Future<String> f = executorService.submit(() -> {
                log.debug("烧草");
                return cooking();
            });
            try {
                log.debug("上菜{}",f.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
    public static void main(String[] args) {

        ExecutorService pool1 = Executors.newFixedThreadPool(1);
        ExecutorService pool2 = Executors.newFixedThreadPool(1);
        //线程1负责点餐，线程2负责做饭
        //两个线程同时被占用，且都在等待菜烧完。此时没有多余的线程处理其他的
        pool1.execute(()->{
            log.debug("处理点餐");
            Future<String> f = pool2.submit(() -> {
                log.debug("烧草");
                return cooking();
            });
            try {
                log.debug("上菜{}",f.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        pool1.execute(()->{
            log.debug("处理点餐");
            Future<String> f = pool2.submit(() -> {
                log.debug("烧草");
                return cooking();
            });
            try {
                log.debug("上菜{}",f.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }
}
