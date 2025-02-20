package io.h_nio.a_file和path.a_file;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 文件demo
 * @author zijian Wang
 */
public class FileDemo {
    public static void main(String[] args) {

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("D:\\dakjhwdlkajlwd.txt"))) {
            reader.readLine();
        } catch (IOException x) {
            //IOException: java.nio.file.NoSuchFileException: D:\dakjhwdlkajlwd.txt
            System.err.format("IOException: %s%n", x);
        }
    }
}
