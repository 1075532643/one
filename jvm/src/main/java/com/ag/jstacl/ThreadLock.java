package com.ag.jstacl;
/*
* 演示线程死锁*/
public class ThreadLock {
    public static void main(String[] args) {
        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (s1){
                    s1.append("a");
                    s2.append("1");
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    synchronized (s2){
                        s1.append("b");
                        s2.append("2");

                        System.out.println(s1);
                        System.out.println(s2);
                    }
                }

            }
        }).start();



        new Thread(new Runnable() {
            @Override
            public void run() {
                    synchronized (s2) {
                        s1.append("a");
                        s2.append("1");
                        try {
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        synchronized (s1) {
                            s1.append("b");
                            s2.append("2");

                            System.out.println(s1);
                            System.out.println(s2);
                        }
                    }


                }

        }).start();


    }
}
