package io.c_缓冲流;

import java.io.*;

/**
 * 演示， 字符缓冲流
 *
 * @author zijian Wang
 */
public class CharacterBuffer {

    /**
     * 使用字符缓冲,读取并写入到文件中
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        String inputPath = "java/target/classes/io/input.txt";
        String outputPath = "java/target/classes/io/output.txt";

        try {
            bufferedReader = new BufferedReader(new FileReader(inputPath));
            bufferedWriter = new BufferedWriter(new FileWriter(outputPath));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                bufferedWriter.write("\n" + line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            bufferedWriter.close();
            bufferedReader.close();
        }
    }
}