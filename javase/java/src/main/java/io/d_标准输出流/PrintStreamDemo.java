package io.d_标准输出流;

import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * 字节输出流，默认输出到控制台
 *
 * @author zijian Wang
 */
public class PrintStreamDemo {
    public static void main(String[] args) throws  Exception{
        PrintStream printStream = null;
        printStream=new PrintStream(new FileOutputStream("D:\\wangzijian.printStream"));
        System.setOut(printStream);
        System.out.println("wangzijian-print1");
        System.out.println("wangzijian-print2");
        System.out.println("wangzijian-print3");
    }
}
