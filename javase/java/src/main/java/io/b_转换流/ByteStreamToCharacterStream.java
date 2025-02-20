package io.b_转换流;

import java.io.*;

/**
 * 将字节流转换位字符流
 *
 * @author zijian Wang
 */
public class ByteStreamToCharacterStream {
    /**
     * 按字节读进来，然后按字符流写出
     *
     * @param args
     */
    public static void main(String[] args) {
        String inputPath = "java/target/classes/io/input.txt";

        FileInputStream inputStream = null;
        BufferedReader bufferedReader = null;

        try {
            inputStream = new FileInputStream(inputPath);
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            System.out.println(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
