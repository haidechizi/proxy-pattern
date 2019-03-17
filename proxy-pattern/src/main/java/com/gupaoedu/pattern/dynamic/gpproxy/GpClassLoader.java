package com.gupaoedu.pattern.dynamic.gpproxy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GpClassLoader extends ClassLoader {

    private File baseFile;

    public GpClassLoader() {
        String filePath = GpClassLoader.class.getResource("").getPath();

        baseFile = new File(filePath);
    }

    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        String className = GpClassLoader.class.getPackage().getName() + "." + name;
        if (baseFile != null) {
            File classFile = new File(baseFile, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()) {
                FileInputStream fis = null;
                ByteArrayOutputStream baos = null;
                try {
                    fis = new FileInputStream(classFile);
                    baos = new ByteArrayOutputStream();
                    byte[] b = new byte[1024];
                    int len;
                    while ((len = fis.read(b)) != -1) {
                        baos.write(b, 0, len);
                    }
                    return defineClass(className, baos.toByteArray(), 0, baos.size());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                    if (baos != null) {
                        try {
                            baos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    classFile.delete();
                }
            }
        }
        return null;
    }
}
