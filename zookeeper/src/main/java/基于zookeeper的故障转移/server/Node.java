package 基于zookeeper的故障转移.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import 基于zookeeper的故障转移.ZkClient;

@Data
@AllArgsConstructor
public class Node {
    private String host;
    private int port;
    private boolean status;
    private ZkClient zkClient;
}


