package zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

import java.io.IOException;

/**
 * zookeeper client 监听指定node的事件。
 */
public class MyZkWatchTest {

    private ZooKeeper zk;
    final static private String connectString = "192.168.56.107:2181,192.168.56.108:2181,192.168.56.109:2181";
    final static private int sessionTimeout = 2000;
    final static String zNode = "/wangzijian";

    public MyZkWatchTest() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                String path = event.getPath();

                if (event.getType() == Event.EventType.None) {
                    // 我们被告知，连接状态已发生变化。连接状态已发生变化,过滤我们不想要的事件（不匹配path）。
                    switch (event.getState()) {
                        case SyncConnected:
                            break;
                        case Expired:
                            // 一切都结束了
                            break;
                    }
                } else {
                    if (path != null && path.equals(zNode)) {
                        System.out.println("查询到" + zNode + "事件!" + event);
                        // 节点上发生了一些变化，让我们重新绑定一探究竟！
                        zk.exists(zNode, true, null, null);//重新绑定监听
                    }
                }
            }
        });
        zk.exists(zNode, true, null, null);//初始化时注册监听
    }

    @Test
    public void testWatcher() throws InterruptedException {
        synchronized (this) {
            while (true) {
                wait();
            }
        }
    }
}