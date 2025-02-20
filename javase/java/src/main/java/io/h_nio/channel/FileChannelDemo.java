package io.h_nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zijian Wang
 */
public class FileChannelDemo {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\wangzijian.txt");
        try (
                FileChannel fileChannel = FileChannel.open(path);
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while (fileChannel.read(byteBuffer) != -1) {
                fileChannel.read(byteBuffer);
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array()));
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
