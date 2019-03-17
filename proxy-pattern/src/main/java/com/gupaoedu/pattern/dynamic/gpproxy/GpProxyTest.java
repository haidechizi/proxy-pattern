package com.gupaoedu.pattern.dynamic.gpproxy;

import com.gupaoedu.pattern.dynamic.jdk.Girl;
import com.gupaoedu.pattern.dynamic.jdk.Person;

public class GpProxyTest {
    public static void main(String[] args) throws Exception {

        Person person = (Person) new MeiPo().getInstance(new Girl());

        person.findLove();
    }
}
