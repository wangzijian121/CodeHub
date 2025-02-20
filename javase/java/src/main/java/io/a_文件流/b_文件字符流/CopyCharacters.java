package io.a_文件流.b_文件字符流;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 按字节读取文件
 *
 * @author zijian Wang
 */
public class CopyCharacters {
    public static void main(String[] args) throws IOException {

        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader("java/target/classes/io/input.txt");
            fileWriter = new FileWriter("java/target/classes/io/output.txt");

            int c;
            while ((c = fileReader.read()) != -1) {
                //实际底层也是按字节读取，每个字符在输出
                char convertedChar = (char) c;
                System.out.println(convertedChar);
                fileWriter.write(c);
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }
}
