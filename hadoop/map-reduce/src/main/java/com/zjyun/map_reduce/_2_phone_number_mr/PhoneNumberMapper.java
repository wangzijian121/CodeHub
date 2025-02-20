package com.zjyun.map_reduce._2_phone_number_mr;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Description: map 类
 * @Author: Wang ZiJian
 * @Date: 2024/10/1
 */
public class PhoneNumberMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    FlowBean flowBean = new FlowBean();


    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context) throws IOException, InterruptedException {

        //1.分割行数据
        String line = value.toString();
        //(13736230513,192.196.100.1,www.atguigu.com,2481,24681,200)
        String[] splitItems = line.split("\t");

        Text phoneNumber = new Text(splitItems[1]);
        long upFlow = Long.parseLong(splitItems[splitItems.length - 3]);
        long downFlow = Long.parseLong(splitItems[splitItems.length - 2]);

        flowBean.setUpFlow(upFlow);
        flowBean.setDownFlow(downFlow);
        flowBean.setSumFlow(upFlow + downFlow);

        //3.组装context
        context.write(phoneNumber, flowBean);
    }
}
