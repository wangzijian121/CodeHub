package 基于zookeeper的分布式锁;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

interface IzkLock {

    /**
     * 获取锁
     *
     * @return
     */
    boolean getLock(MyClient myClient) throws InterruptedException, KeeperException;

    /**
     * 归还锁
     *
     * @return
     */
    boolean close() throws InterruptedException;
}

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/23
 */
public class ZkLock implements IzkLock {

    private final static String PATH = "/locks";
    final static private String connectString = "192.168.56.107:2181,192.168.56.108:2181,192.168.56.109:2181";
    final static private int sessionTimeout = 2000;
    private ZooKeeper zk;

    public ZkLock() throws InterruptedException, KeeperException, IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() != Event.EventType.None) {
                    synchronized (ZkLock.this) {
                        ZkLock.this.notifyAll();
                    }
                }
            }
        });

        if (zk.exists(PATH, false) == null) {
            String s = zk.create(PATH, "root".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("初始化节点成功！" + s);
        }
    }

    @Override
    public boolean getLock(MyClient myClient) throws InterruptedException, KeeperException {
        //如果没有这个节点
        if (myClient.getName() == null) {
            String clientName = zk.create(PATH + "/node_", null,
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            myClient.setName(clientName.replace("/locks/", ""));
            System.out.println("创建等待线程" + clientName);
        }
        if (checkFirstNode(myClient)) {
            //2.是获取到节点
            System.out.println(myClient.getName() + "：已获取锁，正在运行！");
            mockRun();
        } else {
            synchronized (this) {
                wait();
            }
            System.out.println(myClient.getName() + "：已获取锁，正在运行！");
            mockRun();
        }
        return false;
    }

    private void mockRun() throws InterruptedException {
        //模拟运行5秒后关闭
        Thread.sleep(5000);
    }

    private boolean checkFirstNode(MyClient myClient) throws KeeperException, InterruptedException {
        //1.判断自己是是否是最小的序号节点
        List<String> children = zk.getChildren(PATH, false).stream().sorted().collect(Collectors.toList());
        boolean b = children.get(0).equals(myClient.getName());
        if (!b) {
            //3.不是第一个节点，监控前一个节点
            String ahead = children.get(children.indexOf(myClient.getName()) - 1);
            zk.exists(PATH + "/" + ahead, true);
        }
        return b;
    }

    @Override
    public boolean close() throws InterruptedException {
        zk.close();
        return false;
    }
}
