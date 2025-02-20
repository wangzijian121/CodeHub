package io.c_缓冲流;

import java.io.*;

/**
 * 缓冲流
 *
 * @author zijian Wang
 */
public class ByteBuffer {
    /**
     * 使用缓冲字节流读取并写入文件
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String inputPath = "java/target/classes/io/input.txt";
        String outputPath = "java/target/classes/io/output.txt";

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(inputPath));
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputPath));

            int i;
            while ((i = bufferedInputStream.read()) >= 0) {
                System.out.println(i);
                bufferedOutputStream.write(i);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            bufferedOutputStream.close();
            bufferedInputStream.close();
        }

    }
}
