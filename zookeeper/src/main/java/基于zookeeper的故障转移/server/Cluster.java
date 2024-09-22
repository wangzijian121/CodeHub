package 基于zookeeper的故障转移.server;

import lombok.Data;
import org.apache.zookeeper.CreateMode;
import 基于zookeeper的故障转移.ZkClient;

import java.util.HashMap;
import java.util.stream.IntStream;


interface ICluster {

    /**
     * 显示所有节点
     */
    void showAllNode();

    /**
     * 开启集群
     */
    void startCluster();

    /**
     * 关闭集群
     */
    void stopCluster();

    /**
     * 启动节点
     */
    void openNode(String id);

    /**
     * 关闭节点
     */
    void shutDownNode(String id);
}

/**
 * 模拟集群
 *
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/9/22
 */
@Data
public class Cluster {

    protected final static String ROOT_PATH = "/servers";

    //key: id  value:Node 对象
    protected HashMap<String, Node> map = new HashMap<>();
    private ZkClient zkClient;

    public Cluster(int nodeCount) {
        IntStream.range(1, nodeCount + 1).forEach(
                i -> map.put(String.valueOf(i), new Node("192.168.0." + i, 8080, false, new ZkClient())));
        zkClient = new ZkClient();
        if (zkClient.checkNodeExist(ROOT_PATH)) {
            zkClient.createNode(ROOT_PATH, "root", CreateMode.PERSISTENT);
        }
    }
}

class ClusterExecutor implements ICluster {

    private Cluster cluster;

    public ClusterExecutor(Cluster cluster) {
        this.cluster = cluster;
    }

    @Override
    public void showAllNode() {
        cluster.getMap().forEach((x, y) -> System.out.println(("服务器" + x + "号") + (y.isStatus() == true ? "🟢" : "🔴") + y));
    }

    @Override
    public void startCluster() {
        cluster.map.forEach((x, y) -> {
            openNode(x);
        });
        System.out.println("启动集群成功！");
    }

    @Override
    public void stopCluster() {
        cluster.map.forEach((x, y) -> {
            shutDownNode(x);
        });
        System.out.println("关闭集群成功！");
    }

    @Override
    public void openNode(String id) {
        Node node = cluster.getMap().get(id);
        node.setStatus(true);
        node.setZkClient(new ZkClient());
        node.getZkClient().createNode(Cluster.ROOT_PATH + "/node_" + id, node.toString(), CreateMode.EPHEMERAL);
        System.out.println("启动节点成功！");
    }

    @Override
    public void shutDownNode(String id) {
        Node node = cluster.getMap().get(id);
        node.setStatus(false);
        node.getZkClient().close();
        System.out.println("关闭节点成功！");
    }
}
