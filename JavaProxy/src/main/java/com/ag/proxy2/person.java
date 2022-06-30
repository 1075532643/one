package com.ag.proxy2;

import lombok.Data;


public interface person {

    String getName();

    void  setName(String name);

    public void eat();

    public void say(String message);

}
