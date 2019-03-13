package com.gupaoedu.pattern.dynamic.gpproxy;

public class GpClassLoader extends ClassLoader {

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
