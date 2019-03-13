package com.gupaoedu.pattern.dynamic.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMeiPo implements MethodInterceptor {

    private Object obj;

    public Object getInstance(Object obj) {
        this.obj = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object res = methodProxy.invokeSuper(this.obj, objects);
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
