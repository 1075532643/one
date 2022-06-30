package com.ag.proxy2;

import lombok.Data;

@Data
public class enginerPerson implements person{

    private String name;

    public enginerPerson(){}

    public enginerPerson(String name){
        this.name=name;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public void eat() {
        System.out.println("执行吃的方法");
    }

    @Override
    public void say(String message) {
        System.out.println(message);
    }
}
