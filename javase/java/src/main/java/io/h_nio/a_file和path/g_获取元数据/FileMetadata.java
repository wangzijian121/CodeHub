package io.h_nio.a_file和path.g_获取元数据;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 管理文件和目录的元数据
 *
 * @author zijian Wang
 */
public class FileMetadata {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\wangzijian.txt");
        try {
            System.out.println(Files.size(path)+"KB");
            System.out.println(Files.isDirectory(path));
            System.out.println(Files.isRegularFile(path));
            System.out.println(Files.isHidden(path));
            System.out.println(Files.getOwner(path));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
