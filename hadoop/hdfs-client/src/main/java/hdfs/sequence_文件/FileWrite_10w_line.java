package hdfs.sequence_文件;

import java.io.*;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/11/8
 */
public class FileWrite_10w_line {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        File file = new File("D:\\file.txt");
        try (FileWriter fw = new FileWriter(file, true);) {
            for (int i = 0; i < 100000; i++) {
                fw.write(i + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
