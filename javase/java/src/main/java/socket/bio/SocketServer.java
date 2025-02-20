package socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * socket 服务端
 *
 * @author zijian Wang
 */
public class SocketServer {
    public static void main(String[] args) {

        try (
                ServerSocket server = new ServerSocket(8888);
                Socket socket = server.accept();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {

            System.out.println("服务端端口：" + socket.getLocalPort());
            while (true) {
                System.out.println(reader.readLine());
                Thread.sleep(1000);
            }


        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
