package io.h_nio.a_file和path.h_匹配文件和字符串;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 匹配文件
 *
 * @author zijian Wang
 */
public class MatchFile {


    public static void main(String[] args) {

        String match = "glob:**/*.txt";

        final PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher(match);

        //简单的文件访问器，匿名内部类，重写了 visitFile
        SimpleFileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException {
                if (pathMatcher.matches(path)) {
                    //如果匹配到了...进行操作
                    System.out.println("find: " + path.toFile());
                }
                return super.visitFile(path, attrs);
            }
        };

        try {
            Files.walkFileTree(Paths.get("D:\\wangzijian\\"), fileVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
