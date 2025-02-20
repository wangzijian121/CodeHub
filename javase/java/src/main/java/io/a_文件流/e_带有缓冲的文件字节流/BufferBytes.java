package io.a_文件流.e_带有缓冲的文件字节流;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 使用带有缓冲的字符流
 *
 * @author zijian Wang
 */
public class BufferBytes {
    public static void main(String[] args) throws IOException, InterruptedException {
        String outputPath = "java/target/classes/io/output.txt";
        BufferedOutputStream bufferedOutputStream = null;
        try {

            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outputPath));
            bufferedOutputStream.write("buffer_bytes".getBytes());
        } finally {
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        }
    }

}
