package 基于zookeeper的故障转移.server;

import java.util.Scanner;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/22
 */
public class ClusterManager {

    private Cluster cluster;

    private ClusterExecutor clusterExecutor;


    public ClusterManager(Cluster cluster) {
        this.cluster = cluster;
        this.clusterExecutor = new ClusterExecutor(cluster);
    }

    public static void main(String[] args) {
        Cluster cluster = new Cluster(5);
        //5个节点
        ClusterManager clusterManager = new ClusterManager(cluster);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请选择操作：");
            System.out.println("1.启动集群");
            System.out.println("2.关闭集群");
            System.out.println("3.查看当前节点信息");
            System.out.println("4.启动节点");
            System.out.println("5.停止节点");

            Integer next = scanner.nextInt();
            switch (next) {
                case 1:
                    clusterManager.clusterExecutor.startCluster();
                    break;
                case 2:
                    clusterManager.clusterExecutor.stopCluster();
                    break;
                case 3:
                    clusterManager.clusterExecutor.showAllNode();
                    break;
                case 4:
                    System.out.println("请输入开启节点ID：");
                    clusterManager.clusterExecutor.openNode(scanner.next());
                    break;
                case 5:
                    System.out.println("请输入关闭节点ID：");
                    clusterManager.clusterExecutor.shutDownNode(scanner.next());
                    break;
                default:
                    System.out.println("请重新输入！");
            }
        }
    }
}
