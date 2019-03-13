package com.gupaoedu.pattern.dynamic.cglib;

import net.sf.cglib.core.DebuggingClassWriter;

public class CglibProxyTest {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:/Temp/code/cglib");
        CglibMeiPo cglibMeiPo = new CglibMeiPo();
        Son son = (Son) cglibMeiPo.getInstance(new Son());
        son.findLove();

    }
}
