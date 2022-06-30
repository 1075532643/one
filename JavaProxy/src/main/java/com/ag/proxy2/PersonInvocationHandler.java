package com.ag.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/*
* PersonInvocationHandler 类 实现InvocationHandler接口，这个类中持有一个被代理对象(委托类)的实例target。该类别JDK Proxy类回调
 * InvocationHandler 接口中有一个invoke方法，当一个代理实例的方法被调用时，代理方法将被编码并分发到 InvocationHandler接口的invoke方法执行。
 */

public class PersonInvocationHandler<T> implements InvocationHandler {
    /*    * 被代理对象引用，invoke 方法里面method 需要使用这个 被代理对象
     */
    T target;


    public PersonInvocationHandler(T target){
        this.target=target;
    }
    /*proxy  代表动态生成的 动态代理 对象实例
     *method 代表被调用委托类的接口方法，和生成的代理类实例调用的接口方法是一致的，它对应的Method 实例
     * args   代表调用接口方法对应的Object参数数组，如果接口是无参，则为null； 对于原始数据类型返回的他的包装类型。
     * */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("被动态代理类回调执行，代理类，proxy："+proxy.getClass()+"\n  方法名："+ method.getName()+
                "方法。方法返回类型："+method.getReturnType()+
                        "接口方法入参数组"+(args == null ? "null:" : Arrays.toString(args)));
        //MonitorUtil.start();
        TimeUnit.SECONDS.sleep(1);
        Object result = method.invoke(target, args);

        return result;
    }
}
