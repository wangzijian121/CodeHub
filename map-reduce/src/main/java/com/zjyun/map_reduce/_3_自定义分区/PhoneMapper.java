package com.zjyun.map_reduce._3_自定义分区;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/3
 */
public class PhoneMapper extends Mapper<LongWritable, Text, Text,FlowBean> {

    private FlowBean flowBean = new FlowBean();

    private Text text = new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text,  Text,FlowBean>.Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] splitItems = line.split("\t");
        //手机号
        text.set(splitItems[1]);

        //组装对象
        long upFlow = Long.parseLong(splitItems[splitItems.length - 3]);
        long downFlow = Long.parseLong(splitItems[splitItems.length - 2]);

        flowBean.setUpFlow(upFlow);
        flowBean.setDownFlow(downFlow);
        flowBean.setSumFlow(upFlow + downFlow);

        context.write(text,flowBean);
    }
}
