package com.ag.proxy2;

import org.aopalliance.intercept.Invocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class JdkDynamicProxyTest {
    public static void main(String[] args) {
        System.out.println("第一种动态代理");
        enginerPerson enginerPerson = new enginerPerson("villi");
        InvocationHandler handler = new PersonInvocationHandler<>(enginerPerson);
        person personProxy = (person)Proxy.newProxyInstance(person.class.getClassLoader(), new Class<?>[]{person.class}, handler);
        System.out.println("package = " + personProxy.getClass().getPackage()
                + " SimpleName = " + personProxy.getClass().getSimpleName()
                + " name =" +
                personProxy.getClass().getName() + " CanonicalName = " +                ""
                + personProxy.getClass().getCanonicalName() + " 实现的接口 Interfaces = "
                + Arrays.toString(personProxy.getClass().getInterfaces()) +                " superClass = " + personProxy.getClass().getSuperclass() + " methods =" + Arrays.toString(personProxy.getClass().getMethods()));        // 通过 代理类 执行 委托类的代码逻辑

        System.out.println("--分割--");
        personProxy.say("say方法动态代理测试");


        /*
        * 动态代理对象步骤
         *      1、 创建一个与代理对象相关联的 InvocationHandler，以及真实的委托类实例
         *      2、Proxy类的getProxyClass静态方法生成一个动态代理类stuProxyClass，该类继承Proxy类，实现 Person.java接口；JDK动态代理的特点是代理类必须继承Proxy类
         *      3、通过代理类 proxyClass 获得他的带InvocationHandler 接口的构造函数 ProxyConstructor
         *      4、通过 构造函数实例 ProxyConstructor 实例化一个代理对象，并将  InvocationHandler 接口实例传递给代理类。

         * */
    }
}
