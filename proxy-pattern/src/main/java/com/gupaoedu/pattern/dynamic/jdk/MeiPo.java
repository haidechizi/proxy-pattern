package com.gupaoedu.pattern.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * jdk动态代理，只生成一个类，生成代理类效率比较高
 * 但是jdk是使用反射机制，执行效率比较低
 *
 */
public class MeiPo implements InvocationHandler {

    private Object obj;

    public Object getInstance(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object res = method.invoke(obj, args);
        after();
        return res;
    }

    private void before() {

        System.out.println("开始物色对象");

    }

    private void after() {
        System.out.println("找对对象");
    }
}
