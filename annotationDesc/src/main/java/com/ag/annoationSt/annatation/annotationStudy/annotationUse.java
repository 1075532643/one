package com.ag.annoationSt.annatation.annotationStudy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@annatationTest(Desc = "类上面的注解")
public class annotationUse {


    public static void main(String[] args) {

        Annotation annotation1 = annotationUse.class.getAnnotation(annatationTest.class);
        System.out.println("类上面的注解 "+ annotation1);

        Method[] methods = annotationUse.class.getMethods();

        for (Method method : methods) {
            if(method.getName().equals("useAnno")){
                annatationTest annotation = method.getAnnotation(annatationTest.class);
                System.out.println(annotation);
            }

        }


        //  System.out.println("注解的值 "+ annotation.toString());

    }

    @annatationTest(status = annatationTest.Status.NOT_COMPLETE,info = annatationTest.Info.NOT_PAY,Desc = "测试说明")
    public void useAnno(){

        System.out.println("进入方法");
    }
}

