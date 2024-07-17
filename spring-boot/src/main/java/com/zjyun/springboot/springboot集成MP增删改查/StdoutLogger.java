package com.zjyun.springboot.springboot集成MP增删改查;

public class StdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger {
    public void logText(String text) {
        System.err.println("SQL" + text);
    }
}