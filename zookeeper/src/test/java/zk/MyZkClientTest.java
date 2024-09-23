package zk;

import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * zookeeper client 的【增删改查】测试，常用API方法。
 */
public class MyZkClientTest {
    private ZooKeeper zk;
    final static private String connectString = "192.168.56.107:2181,192.168.56.108:2181,192.168.56.109:2181";
    final static private int sessionTimeout = 2000;

    public MyZkClientTest() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("事件：" + watchedEvent);
            }
        });
    }

    @Test
    public void createNode() throws InterruptedException, KeeperException {
        String s = zk.create("/wangzijian/wangzijian", "hello-world".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        System.out.println(s);
    }

    @Test
    public void getNodeData() throws InterruptedException, KeeperException {
        System.out.println("节点数据：" + new String(zk.getData("/wangzijian", false, null)));
    }

    @Test
    public void checkNodeExist() throws InterruptedException, KeeperException {
        System.out.println("是否存在：" + zk.exists("/wangzijian123", true));
        System.out.println("是否存在：" + zk.exists("/asdfasdf", false));
    }

    @Test
    public void deleteNode() throws InterruptedException, KeeperException {
        zk.delete("/wangzijian", -1);
    }

    @Test
    public void getChildren() throws InterruptedException, KeeperException {
        List<String> children = zk.getChildren("/wangzijian", false);
        if (children != null && children.size() > 0) {
            children.forEach(System.out::println);
        }
    }
}