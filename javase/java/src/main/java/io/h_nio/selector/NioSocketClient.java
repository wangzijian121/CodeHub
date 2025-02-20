package io.h_nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

/**
 * 使用nio模型的socket 客户端
 *
 * @author zijian Wang
 */
public class NioSocketClient {

    private static UUID uuid = UUID.randomUUID();
    public static void main(String[] args) throws IOException, InterruptedException {

        Selector selector = Selector.open();
        //SocketChannel
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress("127.0.0.1", 8888));
        channel.register(selector, SelectionKey.OP_CONNECT);
        System.out.println("我的ID：" + uuid);


        while (true) {
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    iterator.remove();
                    //连接到Server端
                    if (selectionKey.isConnectable()) {
                        connectedEvent(selector, channel);
                    } else if (selectionKey.isReadable()) {
                        readEvent(selector, channel, selectionKey);
                    } else if (selectionKey.isWritable()) {
                        writeEvent(selector, channel, selectionKey);
                    }
                }
            }
        }
    }

    private static void writeEvent(Selector selector, SocketChannel channel, SelectionKey selectionKey) throws IOException {
        NioUtils.getChannelInfo(selector);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入对话:");
        String s = scanner.nextLine();
        if ("exit".equals(s)) {
            selectionKey.interestOps(selectionKey.interestOps() - SelectionKey.OP_WRITE);
            NioUtils.doWrite(channel, "exit");
            channel.register(selector, SelectionKey.OP_READ);
            NioUtils.getChannelInfo(selector);
        } else {
            SocketChannel socketChannelWrite = (SocketChannel) selectionKey.channel();
            NioUtils.doWrite(socketChannelWrite, new String(s.getBytes()));
        }
    }

    private static void readEvent(Selector selector, SocketChannel channel, SelectionKey selectionKey) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        socketChannel.configureBlocking(false);
        try {
            socketChannel.read(buffer);
            buffer.flip();
            byte[] dst = new byte[buffer.limit()];
            String content = "";
            buffer.get(dst);
            content += new String(dst);
            System.out.println("【READ】接收到消息：" + content);
            channel.register(selector, SelectionKey.OP_WRITE);

        } catch (IOException e) {
            if (socketChannel != null) {
                socketChannel.close();
            }
            selectionKey.cancel();
            System.out.println("关闭一个连接！");
        }
    }

    private static void connectedEvent(Selector selector, SocketChannel channel) throws IOException {
        if (channel.finishConnect()) {
            NioUtils.doWrite(channel, "hello!!我是客户端,我的线程是：" + uuid);
        }
        channel.register(selector, SelectionKey.OP_READ);
    }
}
