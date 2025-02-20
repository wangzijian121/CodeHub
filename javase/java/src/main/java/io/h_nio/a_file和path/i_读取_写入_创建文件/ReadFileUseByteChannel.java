package io.h_nio.a_file和path.i_读取_写入_创建文件;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author zijian Wang
 */
public class ReadFileUseByteChannel {
    public static void main(String[] args) {

        String path = "D://wangzijian/wangzijian.txt";

        try (SeekableByteChannel seekableByteChannel = Files.newByteChannel(Paths.get(path))) {
            final int BUFFER_CAPACITY = 1024;
            ByteBuffer buf = ByteBuffer.allocate(BUFFER_CAPACITY);
            while (seekableByteChannel.read(buf) > 0) {

                buf.flip();
                System.out.println(Charset.forName("utf-8").decode(buf));
                buf.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
