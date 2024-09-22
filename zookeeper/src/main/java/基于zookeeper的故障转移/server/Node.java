package 基于zookeeper的故障转移.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.zookeeper.CreateMode;
import 基于zookeeper的故障转移.ZkClient;

import java.util.HashMap;

/**
 * 模拟主机节点
 *
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/9/22
 */
interface INode {



}

@Data
@AllArgsConstructor
public class Node {
    private String host;
    private int port;
    private boolean status;
    private ZkClient zkClient;
}


