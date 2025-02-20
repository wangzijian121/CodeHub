package 多线程.java并发编程.java共享模型_不可变.享元模式;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j(topic = "c.Source")
public class ConnectPoolTest {

    public static void main(String[] args) {
        ConnectPool connectPool = new ConnectPool(5);
        connectPool.setPoolMaxSize(20);
        IntStream.range(0, 10).forEach(x -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            new Thread(() -> {

                SourceConnect sourceConnect = connectPool.get();
                if (sourceConnect == null) {
                    return;
                }
                //do something
                sourceConnect.work();
                connectPool.revert(sourceConnect);
            }, "线程" + x).start();
        });
        connectPool.monitor();
    }
}