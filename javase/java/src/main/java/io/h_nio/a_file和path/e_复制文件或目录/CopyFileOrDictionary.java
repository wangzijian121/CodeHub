package io.h_nio.a_file和path.e_复制文件或目录;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * 复制文件和目录
 * REPLACE_EXISTING– 即使目标文件已存在也执行复制。如果目标是符号链接，则复制链接本身（而不是链接的目标）。如果目标是非空目录，则复制失败并出现异常DirectoryNotEmptyException。
 * COPY_ATTRIBUTES– 将与文件关联的文件属性复制到目标文件。支持的确切文件属性取决于文件系统和平台，但last-modified-time跨平台支持并复制到目标文件。
 * NOFOLLOW_LINKS– 表示不应遵循符号链接。如果要复制的文件是符号链接，则复制该链接（而不是链接的目标）。
 *
 * @author zijian Wang
 */
public class CopyFileOrDictionary {

    public static void main(String[] args) {
        Path pathInput = Paths.get("D:\\wangzijian");
        Path pathOutput = Paths.get("D:\\wangzijian123");

        Path fileInput = Paths.get("D:\\wangzijian.txt");
        Path fileOutput = Paths.get("D:\\wangzijian\\wangzijian.txt");


        try {
            Files.copy(pathInput, pathOutput, REPLACE_EXISTING);
            Files.copy(fileInput, fileOutput, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
