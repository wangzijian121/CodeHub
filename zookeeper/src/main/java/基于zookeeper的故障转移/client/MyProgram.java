package 基于zookeeper的故障转移.client;

import lombok.SneakyThrows;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * 请求服务端的程序，需要监听zk 服务端的变化
 *
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/22
 */
public class MyProgram {

    private ZooKeeper zk;
    final static private String connectString = "192.168.56.107:2181,192.168.56.108:2181,192.168.56.109:2181";
    final static private int sessionTimeout = 2000;
    final static String zNode = "/servers";

    public MyProgram() throws IOException, InterruptedException, KeeperException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @SneakyThrows
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
                        List<String> children = zk.getChildren(zNode, true);//重新绑定监听
                        System.out.println("当前可用节点:"+children);
                        children.forEach(x -> {
                            try {
                                byte[] data = zk.getData(zNode + "/" + x, false, null);
                                System.out.println("🟢"+new String(data));
                            } catch (KeeperException e) {
                                throw new RuntimeException(e);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            }
        });
        //初始化时注册监听
        zk.getChildren(zNode, true);
    }

    public void start() throws InterruptedException {
        synchronized (this) {
            while (true) {
                wait();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        MyProgram myProgram = new MyProgram();
        myProgram.start();
    }
}
