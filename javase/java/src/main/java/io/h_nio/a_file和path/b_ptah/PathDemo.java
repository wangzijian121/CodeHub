package io.h_nio.a_file和path.b_ptah;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 通过path获取路径下的文件
 *
 * @author zijian Wang
 */
public class PathDemo {
    public static void main(String[] args) throws IOException {

        /**
         * 去掉冗余
         */
        System.out.println(Paths.get("D:\\项目\\王子健-Java\\..").toRealPath());
        /**
         * 转换为可以从浏览器打开的字符串 ：file:///D:/项目/王子健-Java/../
         */
        System.out.println(Paths.get("D:\\项目\\王子健-Java\\").toUri());
        /**
         * 路径转换为绝对路径
         */
        System.out.println(Paths.get("..\\").toAbsolutePath());

        /**
         * 连接2个路径
         */
        System.out.println(Paths.get("D:\\项目\\").resolve(Paths.get("王子健-Java")));

        /**
         * 比较两个路径
         */
        System.out.println(Paths.get("D:\\项目\\").equals(Paths.get("D:\\项目\\")));
        System.out.println(Paths.get("D:\\项目\\").equals(Paths.get("D:\\项目\\王子健-Java")));

        /**
         * 开头结尾判断
         */
        System.out.println(Paths.get("D:\\项目\\").startsWith("D:\\"));
        System.out.println(Paths.get("D:\\项目\\").endsWith("项目"));
    }
}
