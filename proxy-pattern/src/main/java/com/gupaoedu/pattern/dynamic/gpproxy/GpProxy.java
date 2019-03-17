package com.gupaoedu.pattern.dynamic.gpproxy;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class GpProxy {

    public static final String LN = "\r\n";

    private static int count = 0;

    public static Object newProxyInstance(GpClassLoader loader,
                                          Class<?>[] interfaces,
                                          GpInvocationHandler h)
            throws Exception {
        String generateClassName = "Proxy" + count;

        String proxySrc = generateCode(interfaces, generateClassName);

        System.out.println(proxySrc);
        String filePath = GpProxy.class.getResource("").getPath();
        File file = new File(filePath + generateClassName + ".java");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(proxySrc);
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 3.编译源代码，生成，class文件
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable iterable = manager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, iterable);
        task.call();
        try {
            manager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4.将class文件中的内容动态加载到jvm
        Class proxyClass = loader.findClass(generateClassName);
        Constructor c = proxyClass.getConstructor(GpInvocationHandler.class);

        file.delete();
        // 5.返回被代理的对象
        return c.newInstance(h);

    }



    // TODO
    private static String generateCode0(Class<?>[] interfaces, String generateClassName) {


        String implementClass = "";
        for (int i = 0; i < interfaces.length; i++) {
            implementClass += interfaces[i].getName();
            if (i != interfaces.length - 1) {
                implementClass += ",";
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append(GpProxy.class.getPackage() + ";" + LN);
        sb.append("import java.lang.reflect.Method;" + LN);
        sb.append("public final class " + generateClassName + " extends GpProxy implements " + implementClass + "{" + LN);

        sb.append("private GpInvocationHandler h;" + LN);
        sb.append("public " + generateClassName + "(GpInvocationHandler h) {" + LN);
        sb.append("this.h = h;" + LN);
        sb.append("}" + LN);

        for (Class<?> clazz : interfaces) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {

                String returnType = method.getReturnType().getName();
                sb.append("public final " + returnType + " " + method.getName() + "() {" + LN);
                sb.append("Object obj = null;" + LN);
                sb.append("try {" + LN);
                sb.append("Method m = " + clazz.getName() + ".class.getMethod(\"" + method.getName() + "\"," + method.getParameterTypes() + ");" + LN);

                sb.append("Class[] params = method.getParameterTypes();" + LN );
                sb.append("obj = this.h.invoke(this,m,params);" + LN);
                sb.append("} catch(Throwable e) {" + LN);
                sb.append("e.printStackTrace();" + LN);
                sb.append("}" + LN);
                if ("void".equals(returnType)) {
                    sb.append("return;" + LN);
                } else {
                    sb.append("return obj;" + LN);
                }

                sb.append("}" + LN);
            }
        }


        sb.append("}" + LN);

        // 增加计数器
        count++;

        return sb.toString();

    }


    private static String generateCode(Class<?>[] interfaces, String generateClassName) {


        String implementClass = "";
        for (int i = 0; i < interfaces.length; i++) {
            implementClass += interfaces[i].getName();
            if (i != interfaces.length - 1) {
                implementClass += ",";
            }

        }

        StringBuilder sb = new StringBuilder();
        sb.append(GpProxy.class.getPackage() + ";" + LN);
        sb.append("import java.lang.reflect.Method;" + LN);
        sb.append("public final class " + generateClassName + " extends GpProxy implements " + implementClass + "{" + LN);

        sb.append("private GpInvocationHandler h;" + LN);
        sb.append("public " + generateClassName + "(GpInvocationHandler h) {" + LN);
        sb.append("this.h = h;" + LN);
        sb.append("}" + LN);

        for (Class<?> clazz : interfaces) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {

                String returnType = method.getReturnType().getName();
                sb.append("public final " + returnType + " " + method.getName() + "() {" + LN);
                sb.append("Object obj = null;" + LN);
                sb.append("try {" + LN);
                sb.append("Method m = " + clazz.getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[]{});" + LN);
                sb.append("obj = this.h.invoke(this,m,null);" + LN);
                sb.append("} catch(Throwable e) {" + LN);
                sb.append("e.printStackTrace();" + LN);
                sb.append("}" + LN);
                if ("void".equals(returnType)) {
                    sb.append("return;" + LN);
                } else {
                    sb.append("return obj;" + LN);
                }

                sb.append("}" + LN);
            }
        }


        sb.append("}" + LN);

        // 增加计数器
        count++;

        return sb.toString();

    }
}
