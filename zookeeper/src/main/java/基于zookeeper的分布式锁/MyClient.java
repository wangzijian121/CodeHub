package 基于zookeeper的分布式锁;

import lombok.Data;
import org.apache.zookeeper.KeeperException;

import java.io.IOException;
import java.util.stream.IntStream;

/**
 * 请求的客户端，实际获取锁的类
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/23
 */
@Data
public class MyClient {

    private String name = null;

    public static void main(String[] args) throws InterruptedException, KeeperException, IOException {

        //创建5个线程，同时获取锁
        IntStream.range(0, 5).forEach(x -> {
            new Thread(() -> {
                System.out.println("创建节点" + x);
                ZkLock lock = null;
                try {
                    lock = new ZkLock();
                    lock.getLock(new MyClient());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (KeeperException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        lock.close();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        });
    }
}
