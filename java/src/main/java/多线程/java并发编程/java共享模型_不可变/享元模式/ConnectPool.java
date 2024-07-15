package 多线程.java并发编程.java共享模型_不可变.享元模式;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 通过连接池获取数据源连接。
 * <p>
 * 运行3秒钟结束。✅ <br/>
 * 没获取到阻塞住 CAS✅ <br/>
 * 连接的动态增长与收缩 ✅ <br/>
 * 等待超时时间✅<br/>
 * 链接保活<br/>
 * 分布式 Hash<br/>
 * Test: {@link  多线程.java并发编程.java共享模型_不可变.享元模式.ConnectPoolTest }
 */
@Slf4j(topic = "c.ThreadPool")
public class ConnectPool {

    private final static String PREFIX = "连接";

    private List<SourceConnect> connects;
    /**
     * true:  可以使用
     * false: 不能使用
     */
    private List<Boolean> connectStatus;


    //初始大小
    private int poolInitSize;

    //最大大小
    private int poolMaxSize = 10;

    public void setPoolMaxSize(int poolMaxSize) {
        this.poolMaxSize = poolMaxSize;
    }

    public ConnectPool(int poolInitSize) {
        this.poolInitSize = poolInitSize;
        //初始化连接池
        connects = IntStream.range(0, poolInitSize).mapToObj(x -> {
            SourceConnect sourceConnect = new SourceConnect(PREFIX + x);
            return sourceConnect;
        }).collect(Collectors.toList());

        //初始化状态
        connectStatus = IntStream.range(0, poolInitSize)
                .mapToObj(x -> true)
                .collect(Collectors.toList());
        //缩扩容
        new Thread(() -> shrinkAndExpand(), "容量监视器线程").start();

    }

    private void shrinkAndExpand() {

        log.info("启动扩容缩容！");
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (this) {
                //获取 true的数量
                long count = connectStatus.stream().filter(x -> x == false).count();
                //当前长度
                int size = connectStatus.size();
                //当true等于poolInitSize且小于poolMaxSize是->扩容poolInitSize大小
                if (count == size && count < poolMaxSize) {
                    //扩容连接池
                    IntStream.range(size, size + poolInitSize).mapToObj(x -> {
                        SourceConnect sourceConnect = new SourceConnect(PREFIX + x);
                        return sourceConnect;
                    }).forEach(x -> connects.add(x));

                    //扩容状态池
                    IntStream.range(size, size + poolInitSize)
                            .forEach(x -> connectStatus.add(true));
                    log.info("触发扩容，扩容连接池：" + connectStatus);
                    this.notifyAll();
                }
                //poolSize-true的数量> poolInitSize 时缩容 poolInitSize大小
                if (size - count > poolInitSize) {
                    log.info("触发缩容！");
                    int adder = 0;
                    for (int i = 0; i < connectStatus.size(); i++) {
                        if (adder > 5) {
                            break;
                        }
                        Boolean aBoolean = connectStatus.get(i);
                        if (aBoolean) {
                            connects.remove(i);
                            connectStatus.remove(i);
                            adder++;
                        }
                    }
                    this.monitor();
                }
            }
        }
    }

    /**
     * 获取线程
     *
     * @return
     */
    public SourceConnect get() {
        for (; ; ) {
            //spin
            for (int i = 0; i < connectStatus.size(); i++) {
                if (connectStatus.get(i).booleanValue() == true) {
                    SourceConnect sourceConnect = connects.get(i);
                    if (sourceConnect != null) {
                        connectStatus.set(i, false);
                        return sourceConnect.connect();
                    } else {
                        //wait
                        long timeout = 20000;
                        long passedTime = 0;
                        long startTime = System.currentTimeMillis();
                        synchronized (this) {
                            while (!connectStatus.stream().filter(x -> x == true).findFirst().isPresent()) {
                                if (passedTime > timeout) {
                                    log.error(Thread.currentThread().getName() + "超时🕒");
                                    return null;
                                }
                                //考虑到虚假唤醒的情况，虚假唤醒后需要等待(timeout - passedTime)时间
                                try {
                                    this.wait(timeout - passedTime);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                passedTime = System.currentTimeMillis() - startTime;
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 连接关闭
     */
    public void revert(SourceConnect sourceConnect) {
        for (int i = 0; i < connects.size(); i++) {
            SourceConnect matchSourceConnect = connects.get(i);
            if (matchSourceConnect == sourceConnect) {
                //回收
                if (matchSourceConnect != null) {
                    matchSourceConnect.close();
                    connectStatus.set(i, true);
                    synchronized (this) {
                        this.notifyAll();
                    }
                } else {
                    log.error("回收失败！");
                }
            }
        }
    }

    public void monitor() {
        System.out.println("👀当前线程池状态：" + connectStatus);
    }

}
