package 基于zookeeper的故障转移;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/22
 */
public class ZkClient {

    private ZooKeeper zk;
    final static private String connectString = "192.168.56.107:2181,192.168.56.108:2181,192.168.56.109:2181";
    final static private int sessionTimeout = 2000;

    public ZkClient() {
        try {
            zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("事件：" + watchedEvent);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createNode(String path, String data,CreateMode createMode) {
        try {
            zk.create(path, data.getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkNodeExist(String path){
        try {
            return zk.exists(path, false) == null;
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  void close() {
        try {
            zk.close();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
