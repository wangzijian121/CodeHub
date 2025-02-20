package socket.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author zijian Wang
 */
public class NioUtils {

    /**
     * 写数据到通道中
     *
     * @param sc
     * @param data
     * @throws IOException
     */
    public static void doWrite(SocketChannel sc, String data) throws IOException {
        byte[] req = data.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(req.length);
        byteBuffer.put(req);
        byteBuffer.flip();
        sc.write(byteBuffer);
    }

    /**
     * 获取通道信息
     *
     * @param selector
     */
    public static void getChannelInfo(Selector selector) {
        System.out.println("〰〰〰〰〰〰〰〰ChannelInfo〰〰〰〰〰");
        System.out.println("所有通道数：" + selector.keys().size());
        System.out.println("已选择通道数：" + selector.selectedKeys().size());

        // 遍历选择键并输出类型
        System.out.print("所有通道：");
        for (SelectionKey key : selector.keys()) {
            int ops = key.interestOps();
            if ((ops & SelectionKey.OP_ACCEPT) != 0) {
                System.out.print("OP_ACCEPT ");
            }

            if ((ops & SelectionKey.OP_READ) != 0) {
                System.out.print("OP_READ ");
            }
            if ((ops & SelectionKey.OP_WRITE) != 0) {
                System.out.print("OP_WRITE ");
            }
        }
        System.out.println();

        // 遍历选择键并输出类型
        System.out.print("已选择通道：");
        for (SelectionKey key : selector.selectedKeys()) {
            int ops = key.interestOps();
            if ((ops & SelectionKey.OP_ACCEPT) != 0) {
                System.out.print("OP_ACCEPT ");
            }

            if ((ops & SelectionKey.OP_READ) != 0) {
                System.out.print("OP_READ ");
            }
            if ((ops & SelectionKey.OP_WRITE) != 0) {
                System.out.print("OP_WRITE ");
            }
        }
        System.out.println();
        System.out.println("〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰〰");
    }
}
