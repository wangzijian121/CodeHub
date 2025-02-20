package io.h_nio.a_file和path.d_删除文件或目录;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 删除文件
 * 1.您可以删除文件、目录或链接。
 * 2.对于符号链接，链接被删除，而不是链接的目标。
 * 3.对于目录，目录必须为空，否则删除失败。
 *
 * @author zijian Wang
 */
public class DeleteFileOrDictionary {
    public static void main(String[] args) {
//        删除文件
        Path path = Paths.get("D:\\wangzijian.txt");
        //删除目录
        Path path2 = Paths.get("D:\\wangzijian");
        try {
//            Files.delete(path);
            Files.delete(path2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
