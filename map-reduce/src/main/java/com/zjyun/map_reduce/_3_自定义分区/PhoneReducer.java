package com.zjyun.map_reduce._3_自定义分区;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 传入： 13736230513 FlowBean(upFlow=100, downFlow=100, sumFlow=200)
 * <p>
 * 传出：13736230513  100, 100,  200（FlowBean 的toString）
 *
 * @Description: reduce 类
 * @Author: Wang ZiJian
 * @Date: 2024/10/1
 */
public class PhoneReducer extends Reducer<FlowBean, Text, Text, Text> {

    @Override
    protected void reduce(FlowBean key, Iterable<Text> values, Reducer<FlowBean, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        Text text = new Text(key.toString());
        context.write(values.iterator().next(), text);
    }
}