package io.f_对象流;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author zijian Wang
 */
public class ObjectOutputStreamDemo {
    public static void main(String[] args) {

        ObjectOutputStream outputStream = null;
        String outputPath = "java/target/classes/io/output.txt";
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(outputPath));
            outputStream.writeObject(new Person("wangzijian", 30));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

// 定义一个简单的类
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    // 构造方法
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
