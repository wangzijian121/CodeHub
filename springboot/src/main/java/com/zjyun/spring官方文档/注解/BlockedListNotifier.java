package com.zjyun.spring官方文档.注解;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 邮件警报器
 */
public class BlockedListNotifier implements ApplicationListener<BlockedListEvent> {

    private String notificationAddress;

    public void setNotificationAddress(String notificationAddress) {
        this.notificationAddress = notificationAddress;
    }

    public void onApplicationEvent(BlockedListEvent event) {
        // notify appropriate parties via notificationAddress...
    }
}