package com.zjyun.springboot.springboot集成mp增删改查.config;

public class StdoutLogger extends com.p6spy.engine.spy.appender.StdoutLogger {
    public void logText(String text) {
        System.err.println("SQL" + text);
    }
}