package 多线程.java并发编程.java共享模型_不可变.不可变类;

public class MyFinal {
    final int num =20;

    public static void main(String[] args) {
        final String name = "wangzijian";
        System.out.println(name);
    }
    /***
     * // class version 52.0 (52)
     * // access flags 0x21
     * public class 多线程/java并发编程/java共享模型_不可变/不可变类/MyFinal {
     *
     *   // compiled from: MyFinal.java
     *
     *   // access flags 0x10
     *   final I num = 20
     *
     *   // access flags 0x1
     *   public <init>()V
     *    L0
     *     LINENUMBER 3 L0
     *     ALOAD 0
     *     INVOKESPECIAL java/lang/Object.<init> ()V
     *    L1
     *     LINENUMBER 4 L1
     *     ALOAD 0
     *     BIPUSH 20
     *    💡💡💡 使用了内存屏障 👉PUTFIELD 多线程/java并发编程/java共享模型_不可变/不可变类/MyFinal.num : I
     *     RETURN
     *    L2
     *     LOCALVARIABLE this L多线程/java并发编程/java共享模型_不可变/不可变类/MyFinal; L0 L2 0
     *     MAXSTACK = 2
     *     MAXLOCALS = 1
     *
     *   // access flags 0x9
     *   public static main([Ljava/lang/String;)V
     *    L0
     *     LINENUMBER 7 L0
     *     LDC "wangzijian"
     *     ASTORE 1
     *    L1
     *     LINENUMBER 8 L1
     *     GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
     *     LDC "wangzijian"
     *     INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
     *    L2
     *     LINENUMBER 9 L2
     *     RETURN
     *    L3
     *     LOCALVARIABLE args [Ljava/lang/String; L0 L3 0
     *     LOCALVARIABLE name Ljava/lang/String; L1 L3 1
     *     MAXSTACK = 2
     *     MAXLOCALS = 2
     * }
     */
}
