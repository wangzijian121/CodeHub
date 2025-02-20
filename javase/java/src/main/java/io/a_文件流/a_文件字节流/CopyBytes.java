package io.a_文件流.a_文件字节流;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 按字符读取文件（底层实际也是按照字节读取）
 *
 * @author zijian Wang
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException {

        String inputPath = "java/target/classes/io/input.txt";
        String outputPath = "java/target/classes/io/output.txt";

        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            in = new FileInputStream(inputPath);
            out = new FileOutputStream(outputPath);
            int c;

            while ((c = in.read()) != -1) {
                System.out.println(c);
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}