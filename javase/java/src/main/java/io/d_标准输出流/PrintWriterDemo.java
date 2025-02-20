package io.d_标准输出流;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author zijian Wang
 */
public class PrintWriterDemo {
    public static void main(String[] args) {

        try (
                PrintWriter printWriter = new PrintWriter("D:\\wangzijian.print");
        ) {
            printWriter.println("wangzijian");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
