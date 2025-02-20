package com.zjyun.map_reduce._2_phone_number_mr;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * 传入： 13736230513 FlowBean(upFlow=100, downFlow=100, sumFlow=200)
 *
 * 传出：13736230513  100, 100,  200（FlowBean 的toString）
 * @Description: reduce 类
 * @Author: Wang ZiJian
 * @Date: 2024/10/1
 */
public class PhoneNumberReducer extends Reducer<Text, FlowBean, Text, Text> {

    private  FlowBean sumFlowBean = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Reducer<Text, FlowBean, Text, Text>.Context context) throws IOException, InterruptedException {

        long totalUpFlow=0;
        long totalDownFlow=0;
        long totalFlow=0;

        for (FlowBean value : values) {
            totalUpFlow += value.getUpFlow();
            totalDownFlow += value.getDownFlow();
            totalFlow+=value.getSumFlow();
        }
        sumFlowBean.setUpFlow(totalUpFlow);
        sumFlowBean.setDownFlow(totalDownFlow);
        sumFlowBean.setSumFlow(totalFlow);
        context.write(key, new Text(sumFlowBean.toString()));
    }
}
