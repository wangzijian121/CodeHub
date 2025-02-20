package io.g_bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zijian Wang
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        try (
                ServerSocket serverSocket = new ServerSocket(8888);
                Socket socket = serverSocket.accept();
                BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            System.out.println(bufferedInputStream.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
