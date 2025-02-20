package io.h_nio.a_file和path.f_移动文件或文件夹;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * REPLACE_EXISTING– 即使目标文件已存在也执行移动。如果目标是符号链接，则符号链接将被替换，但它指向的内容不受影响。
 * ATOMIC_MOVE– 将移动作为原子文件操作执行。如果文件系统不支持原子移动，则会引发异常。使用 ，ATOMIC_MOVE您可以将文件移动到目录中，并保证监视该目录的任何进程都可以访问完整的文件。
 *
 * @author zijian Wang
 */
public class MoveFileOrDictionary {

    public static void main(String[] args) {
        Path fileInput = Paths.get("D:\\wangzijian.txt");
        Path fileOutput = Paths.get("D:\\wangzijian\\wangzijian.txt");

        try {
            Files.move(fileInput, fileOutput, REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
