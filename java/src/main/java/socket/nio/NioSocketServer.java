package socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 使用nio模型的socket 服务端
 * <p>
 * 1. selector 拿着注册进来的【OP_ACCEPT】【 OP_READ 】等。
 * 每次轮询：
 * 当channel中有数据时（IO准备好了数据，可以读），执行读buffer操作  selectedKeys = OP_READ
 * 当管道中满足写操作时，触发写事件，执行写buffer操作（写完后需要手动结束，也就是去掉interestOps） selectedKeys=OP_WRITE
 *
 * @author zijian Wang
 */
public class NioSocketServer extends Thread {


    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.configureBlocking(false);
        channel.bind(new InetSocketAddress(8888));
        Selector selector = Selector.open();
        //准备事件
        channel.register(selector, SelectionKey.OP_ACCEPT);
        SelectionKey selectionKey = null;
        NioUtils.getChannelInfo(selector);

        int i = 1;
        while (selector.select() > 0) {
            NioUtils.getChannelInfo(selector);
            System.out.println("第" + (i++) + "次select");
            //获取到select 的所有SelectionKey，然后遍历
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isAcceptable()) {

                    acceptEvent(channel, selector);

                } else if (selectionKey.isReadable()) {

                    readEvent(selector, selectionKey);

                } else if (selectionKey.isWritable()) {

                    writeEvent(selectionKey);

                }
            }
        }
    }

    private static void writeEvent(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        NioUtils.doWrite(socketChannel, "you are exit!");
        selectionKey.interestOps(selectionKey.interestOps() - SelectionKey.OP_WRITE);
    }

    private static void readEvent(Selector selector, SelectionKey selectionKey) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        socketChannel.configureBlocking(false);
        try {
            socketChannel.read(buffer);
            buffer.flip();
            //获取客户端的
            if (new String(buffer.array()).contains("exit")) {
                System.out.println("开始广播！");
                socketChannel.register(selector, SelectionKey.OP_WRITE);
                NioUtils.getChannelInfo(selector);
            } else {
                byte[] dst = new byte[buffer.limit()];
                String content = "";
                buffer.get(dst);
                content += new String(dst);
                System.out.println("【READ】接收到消息：" + content);
            }
            NioUtils.getChannelInfo(selector);

        } catch (IOException e) {
            if (socketChannel != null) {
                socketChannel.close();
            }
            selectionKey.cancel();
            System.out.println("关闭一个连接！");
        }
    }

    private static void acceptEvent(ServerSocketChannel channel, Selector selector) throws IOException {
        SocketChannel socketChannel = channel.accept();
        socketChannel.configureBlocking(false);
        //将此通道注册到给定的选择器，并返回选择键。
        //选择器检测到相应的通道已准备好读取
        socketChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("【ACCEPT】客戶端已连接");
        NioUtils.doWrite(socketChannel, "您已链接到服务端！");
    }
}
