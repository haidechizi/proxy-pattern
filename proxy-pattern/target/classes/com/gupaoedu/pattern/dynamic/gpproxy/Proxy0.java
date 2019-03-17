package com.gupaoedu.pattern.dynamic.gpproxy;
import java.lang.reflect.Method;
public final class Proxy0 extends GpProxy implements com.gupaoedu.pattern.dynamic.jdk.Person{
private GpInvocationHandler h;
public Proxy0(GpInvocationHandler h) {
this.h = h;
}
public final void findLove() {
Object obj = null;
try {
Method m = com.gupaoedu.pattern.dynamic.jdk.Person.class.getMethod("findLove",[Ljava.lang.Class;@2503dbd3);
Class[] params = method.getParameterTypes();
obj = this.h.invoke(this,m,params);
} catch(Throwable e) {
e.printStackTrace();
}
return;
}
}
