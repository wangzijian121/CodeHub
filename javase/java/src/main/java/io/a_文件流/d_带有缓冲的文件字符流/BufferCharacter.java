package io.a_文件流.d_带有缓冲的文件字符流;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 使用带有缓冲的字符流
 *
 * @author zijian Wang
 */
public class BufferCharacter {
    public static void main(String[] args) throws IOException, InterruptedException {
        String outputPath = "java/target/classes/io/output.txt";
        BufferedWriter bufferedWriter = null;
        try {

            bufferedWriter = new BufferedWriter(new FileWriter(outputPath));
            bufferedWriter.write("buffer_character");
            bufferedWriter.flush();
            Thread.sleep(10000000);
            bufferedWriter.write("\nbuffer1234");
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

}
