package 分布式锁框架_curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.stream.IntStream;

public class CuratorConfig {
    public static void main(String[] args) {

        // 重试策略，这里使用的是指数补偿重试策略，重试3次，初始重试间隔1000ms，每次重试之后重试间隔递增。
        RetryPolicy retry = new ExponentialBackoffRetry(1000, 3);
        // 初始化Curator客户端：指定链接信息 及 重试策略
        CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.56.107:2181,192.168.56.108:2181,192.168.56.109:2181", retry);
        client.start(); // 开始链接，如果不调用该方法，很多方法无法工作
        InterProcessMutex mutex = new InterProcessMutex(client, "/curators");
        IntStream.range(0,5).forEach(x-> new Thread(() -> {
            try {
                mutex.acquire();
                Thread.sleep(5000);
                System.out.println("执行完毕！");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    mutex.release();
                    System.out.println("关闭分布式锁！");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start());
   }
}
