package io.g_bio;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author zijian Wang
 */
public class BioClient {
    public static void main(String[] args) {

        try (
                Socket socket = new Socket("127.0.0.1", 8888);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        ) {
            String message = "wangzijian";
            bufferedOutputStream.write(message.getBytes());
            bufferedOutputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
