package com.gupaoedu.pattern.dynamic.gpproxy;

import java.lang.reflect.Method;

public class MeiPo implements GpInvocationHandler {

    private Object obj;

    public Object getInstance(Object obj) throws Exception {
        this.obj = obj;
        return GpProxy.newProxyInstance(new GpClassLoader(), obj.getClass().getInterfaces(), this);

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);
        return result;
    }
}
