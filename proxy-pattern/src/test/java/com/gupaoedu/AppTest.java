package com.gupaoedu;

import static org.junit.Assert.assertTrue;

import com.gupaoedu.pattern.dynamic.jdk.Girl;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        Method[] methods = Test.class.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getModifiers());
            System.out.println(method.getModifiers());
        }
    }
}
