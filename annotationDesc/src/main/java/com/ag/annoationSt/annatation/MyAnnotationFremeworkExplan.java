package com.ag.annoationSt.annatation;

import com.ag.annoationSt.annatation.annotationStudy.afterTest;
import com.ag.annoationSt.annatation.annotationStudy.beforeTest;
import com.ag.annoationSt.annatation.annotationStudy.classTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyAnnotationFremeworkExplan {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Class<classTest> classTestClass = classTest.class;
        classTest classTest = classTestClass.newInstance();
        Method[] methods = classTestClass.getMethods();

        List<Method> myBeforeList = new ArrayList<>();
        List<Method> myAfterList = new ArrayList<>();
        List<Method> myTestList = new ArrayList<>();

        for (Method method : methods) {
            if(method.isAnnotationPresent(beforeTest.class)){
                myBeforeList.add(method);
            }else if(method.isAnnotationPresent(MyTest.class)){
                myTestList.add(method);
            }else if(method.isAnnotationPresent(afterTest.class)){
                myAfterList.add(method);
            }
            
        }
        for (Method method : myAfterList) {
            for (Method method1 : myBeforeList) {
                method1.invoke(classTest);
            }

            method.invoke(classTest);
        }

    }
}
