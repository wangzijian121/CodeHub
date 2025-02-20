package io.h_nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * nio的channel
 * 准备  D盘下创建: wangzijian.txt
 *
 * @author zijian Wang
 */
public class NioChannel {


    public static void main(String[] args) {
//        read_01();
//        read_02();
//        read_03();
//        writer();
        transfer();
    }

    /**
     * 使用BufferedInputStream缓冲字节流去读
     */
    private static void read_01() {
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream("D:\\wangzijian.txt"));
                ReadableByteChannel readableByteChannel = Channels.newChannel(bufferedInputStream);
        ) {
            ByteBuffer dst = ByteBuffer.allocate(10);
            readableByteChannel.read(dst);
            System.out.println(new String(dst.array()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用文件字节流获取FileChannel
     */
    private static void read_02() {
        try (
                FileInputStream fileInputStream = new FileInputStream("D://wangzijian.txt");
                FileChannel fileChannel = fileInputStream.getChannel();
        ) {
            ByteBuffer dst = ByteBuffer.allocate(10);
            fileChannel.read(dst);
            System.out.println(new String(dst.array()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用文件字节流获取FileChannel
     */
    private static void read_03() {
        try (
                SeekableByteChannel fileChannel = Files.newByteChannel(Paths.get("D://wangzijian.txt"));
        ) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (fileChannel.read(byteBuffer) > 0) {
                byteBuffer.flip();
                System.out.print(Charset.forName("UTF-8").decode(byteBuffer));
                byteBuffer.clear();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用文件字节流获取FileChannel
     * 读取文件中的数据，使用多个buffer写入到另一个文件中。
     */
    private static void writer() {
        try (
                FileInputStream fileInputStream = new FileInputStream("D://wangzijian.txt");
                FileChannel inputStreamChannel = fileInputStream.getChannel();
                FileOutputStream fileOutputStream = new FileOutputStream("D://wangzijian_w1.txt");
                FileChannel outputStreamChannel = fileOutputStream.getChannel();
        ) {
            ByteBuffer dst1 = ByteBuffer.allocate(5);
            ByteBuffer dst2 = ByteBuffer.allocate(5);
            ByteBuffer[] byteBuffers = new ByteBuffer[]{dst1, dst2};

            inputStreamChannel.read(byteBuffers);
            dst1.flip();
            dst2.flip();
            System.out.println(new String(dst1.array()));
            System.out.println(new String(dst2.array()));

            outputStreamChannel.write(byteBuffers);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     */
    private static void transfer() {

        try (
                FileInputStream fileInputStream = new FileInputStream("D://wangzijian.txt");
                FileChannel inputStreamChannel = fileInputStream.getChannel();

                FileOutputStream fileOutputStream = new FileOutputStream("D://wangzijian_w1.txt");
                FileChannel outputStreamChannel = fileOutputStream.getChannel();
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(20);

            inputStreamChannel.read(buffer);

            //将字节从给定的可读字节通道传输到此通道的文件中
            outputStreamChannel.transferFrom(inputStreamChannel,0,buffer.capacity());
            //将字节从此通道的文件传输到给定的可写字节通道。
            inputStreamChannel.transferTo(0,buffer.capacity(),outputStreamChannel);

            buffer.flip();
            System.out.println(new String(buffer.array()));
            outputStreamChannel.write(buffer);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
