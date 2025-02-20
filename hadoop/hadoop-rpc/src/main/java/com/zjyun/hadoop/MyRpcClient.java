package com.zjyun.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/15
 */
public class MyRpcClient implements MyProtocol {

    final static long versionID = 1;
    private static final Logger log = LoggerFactory.getLogger(MyRpcClient.class);
    private MyProtocol proxy;

    public MyRpcClient() {
        //创建客户端的代理对象
        try {
            proxy = RPC.getProxy(MyProtocol.class, versionID,
                    new InetSocketAddress("127.0.0.1", 8888), new Configuration());
        } catch (IOException e) {
            e.printStackTrace();
            log.error("client 代理对象创建失败！");
        }
    }

    @Override
    public void mkdir(String path) {
        try {
            proxy.mkdir("/input");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        MyRpcClient client = new MyRpcClient();
        client.mkdir("/input");
    }
}
