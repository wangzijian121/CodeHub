package scala_to_java;

import scala.jdk.javaapi.CollectionConverters;

public class JavaClass {
    public static void main(String[] args) {
        // java
        //创建 Scala 类的实例
        ScalaClass sc = new ScalaClass();
        //以 'sc.strings（）' 的形式访问 'strings' 字段
        scala.collection.immutable.List<String> xs = sc.strings();
        // 将 Scala 的 'List' 转换为 Java 的 'List<String>'
        java.util.List<String> listOfStrings = CollectionConverters.asJava(xs);
        listOfStrings.forEach(System.out::println);
    }
}
