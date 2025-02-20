package com.zjyun.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/15
 */

/**
 * 自定义协议类
 *
 * @author Wang ZiJian
 */

public class MyRpcServer implements MyProtocol {

    public static void main(String[] args) throws IOException {

        RPC.Server myServer = new RPC.Builder(new Configuration())
                .setBindAddress("127.0.0.1")
                .setPort(8888)
                .setProtocol(MyProtocol.class)
                .setInstance(new MyRpcServer())
                .build();
        myServer.start();
    }

    @Override
    public void mkdir(String path) {
        System.out.println("创建path：" + path);
    }

}

