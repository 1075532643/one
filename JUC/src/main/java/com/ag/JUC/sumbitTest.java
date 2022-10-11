package com.ag.JUC;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j(topic = "submit.test")
public class sumbitTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    return "1";

                },
                () -> {
                    log.debug("proceed");
                    Thread.sleep(1000);
                    return "2";
                },
                //第三个任务出现的慢是因为只创建了两个线程
                () -> {
                    log.debug("end");
                    Thread.sleep(2000);
                    return "3";
                }
        ));
       futures.forEach(f->{
           try {
               log.debug("{}",f.get());
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           } catch (ExecutionException e) {
               throw new RuntimeException(e);
           }
       });
    }

    private static void meehod1(ExecutorService executorService) throws ExecutionException, InterruptedException {

        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "ok";
            }
        });
        log.debug("{}",submit.get());
    }
}
