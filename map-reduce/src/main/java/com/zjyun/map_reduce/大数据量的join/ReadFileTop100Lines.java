package com.zjyun.map_reduce.大数据量的join;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileTop100Lines {

    public static void main(String[] args) {

        readTop100Lines("D:\\项目\\王子健-Java\\Java\\map-reduce\\order.txt");
    }

    private static void readTop100Lines(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            while ((line = reader.readLine()) != null && lineCount < 100) {
                System.out.println(line);
                lineCount++;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}