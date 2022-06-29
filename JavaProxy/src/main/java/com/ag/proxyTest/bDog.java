package com.ag.proxyTest;

public class bDog implements Dog{
    @Override
    public void eat() {
        System.out.println("eat");
        return ;
    }

    @Override
    public void doSomething() {
        System.out.println("do");
     return;
    }
}
