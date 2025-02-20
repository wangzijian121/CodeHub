package socket.bio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * socket 客户端
 * @author zijian Wang
 */
public class SocketClient {
    public static void main(String[] args) {

        String host = "127.0.0.1";
        int port = 8888;
        try (
                Socket client = new Socket(host, port);
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        ) {
            if (client.isConnected()) {
                System.out.println("client 已连接！");
            }
            writer.write("hello world");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
