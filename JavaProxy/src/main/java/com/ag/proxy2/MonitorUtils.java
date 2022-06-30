package com.ag.proxy2;

public class MonitorUtils {
    private static ThreadLocal<Long> t1 = new ThreadLocal<>();

    public static void start(){
        t1.set(System.currentTimeMillis());
    }

    public static void finish(String methodName){
        Long finish = System.currentTimeMillis();
        System.out.println(methodName +"方法执行耗时"+ (finish-t1.get())+"");
    }

}
