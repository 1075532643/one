package com.ag.annoationSt.annatation.alis;

import javax.swing.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
public class alisforTest {

    /*public static void main(String[] args) {

        Method[] methods = alisforTest.class.getMethods();
        for (Method method : methods) {
            if(method.getName().equals("alis")){

                aliasTestA annotation = method.getAnnotation(aliasTestA.class);
                aliasTestB annotation1 = method.getAnnotation(aliasTestB.class);
                System.out.println(annotation);
                System.out.println(annotation1);
            }
        }
    }*/

    @aliasTestA(a1 = "这是a1测试")
    @aliasTestB("测试b类")
    public void alis(){}
}
