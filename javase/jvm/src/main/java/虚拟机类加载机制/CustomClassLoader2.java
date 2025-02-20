package 虚拟机类加载机制;

/**
 * 准备：D:\tmp\test\com\zjyun\ 创建一个java 并编译为.class
 *
 * 破坏了 双亲委派模型 的自定义类加载器
 * 如果自定义的path 下有，则加载
 * 如果自定义的path 下没有，则加载父加载器的path去寻找依赖
 */

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader2 extends ClassLoader {

    private String basePath;

    public CustomClassLoader2(String basePath) {
        this.basePath = basePath;
    }

    @Override
    public Class<?> findClass(String name) {

        byte[] b = loadClassData(name);
        //defineClass将定义字节数组转为对象
        return defineClass(name, b, 0, b.length);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            return this.findClass(name);
        } catch (Exception e) {
            return super.loadClass(name);
        }
    }

    //从连接加载类数据
    private byte[] loadClassData(String name) {
        //将指定目录下的.class文件 以文件的形式加载进来
        String className = name.replace(".", "\\") + ".class";
        //将我自己要加载的jar包转为byte数组返回, 我这里使用了自己的本地路径.
        File file = new File(basePath + className);

        System.out.println("file:" + file);

        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            int b = 0;
            while ((b = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(b);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes;

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (byteArrayOutputStream != null) {

                    byteArrayOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {

        CustomClassLoader2 customClassLoader = new CustomClassLoader2("D:\\tmp\\test\\com\\zjyun\\");

        System.out.println(System.getProperty("java.class.path").replace(";", "\n"));

        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(customClassLoader.getClass().getClassLoader());
        //sun.misc.Launcher$ExtClassLoader@7f31245a
        System.out.println(customClassLoader.getClass().getClassLoader().getParent());
        //null
        System.out.println(customClassLoader.getClass().getClassLoader().getParent().getParent());

        Class c = customClassLoader.loadClass("MyInfo");
        try {

            Object o = c.newInstance();

            //com.zjyun.MyInfo 信息
            System.out.println(o);
            //看看MyInfo这个类的ClassLoader是谁？
            System.out.println("This Class Loader is " + o.getClass().getClassLoader());
            System.out.println("This Class Loader.getParent  is" + o.getClass().getClassLoader().getParent());

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

