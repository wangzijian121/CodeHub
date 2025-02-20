package io.h_nio.a_file和path.i_读取_写入_创建文件;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

/**
 * 读取_写入_创建文件
 *
 * @author zijian Wang
 */
public class ReadAndWriteAndCreate {
    public static void main(String[] args) {
        Path path = Paths.get("D://wangzijian/wangzijian.txt");
        try {
            //readAllBytes
            byte[] bytes = Files.readAllBytes(path);
            System.out.println(bytes);
            //readLine
            List<String> strings = Files.readAllLines(path);
            System.out.println(strings.get(0));

            //newBufferedReader
            try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
                bufferedReader.readLine();
            }

            //newBufferedWriter
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path)) {
                bufferedWriter.write("wangzijian_write_demo"+new Date());
            }

            Files.createFile(Paths.get("D://wangzijian_123.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
