package io.h_nio.a_file和path.c_检查文件或目录;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 检查当前文件状态：
 *  1.该文件已验证存在。
 *  2.该文件经验证不存在。
 *  3.文件的状态未知。当程序无权访问该文件时，可能会出现此结果。
 *
 * @author zijian Wang
 */
public class CheckFileOrDictionary {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\项目");
        Path path2 = Paths.get("D:\\项目123");
        Path file = Paths.get("D:\\wangzijian.txt");

        System.out.println(Files.exists(path));
        System.out.println(Files.exists(path2));
        System.out.println(Files.exists(file));
    }
}
