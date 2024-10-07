package com.zjyun.map_reduce._3_自定义分区;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/3
 */
public class PhonePartitioner extends Partitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String split_3_phone = text.toString().substring(0, 3);
        if (split_3_phone.equals("136")) {
            return 0;
        }
        if (split_3_phone.equals("137")) {
            return 1;
        }
        if (split_3_phone.equals("138")) {
            return 2;
        } else {
            return 3;
        }
    }
}
