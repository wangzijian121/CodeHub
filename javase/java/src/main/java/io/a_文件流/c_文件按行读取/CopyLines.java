package io.a_文件流.c_文件按行读取;

import java.io.*;

public class CopyLines {
    public static void main(String[] args) throws IOException {

        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        String inputPath = "java/target/classes/io/input.txt";
        String outputPath = "java/target/classes/io/output.txt";

        try {
            inputStream = new BufferedReader(new FileReader(inputPath));
            outputStream = new PrintWriter(new FileWriter(outputPath));

            String l;
            while ((l = inputStream.readLine()) != null) {
                System.out.println(new String(l));
                outputStream.println(l);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}