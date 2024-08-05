package com.zjyun.starter.utils;
 
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class WoniuUtil {
 
    private String pattern; // 日期格式
 
    public WoniuUtil(String pattern) {
        this.pattern = pattern;
    }
 
    public String dateToStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }
}