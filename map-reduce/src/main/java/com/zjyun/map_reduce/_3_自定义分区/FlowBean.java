package com.zjyun.map_reduce._3_自定义分区;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Description: 自定义序列化类
 * @Author: Wang ZiJian
 * @Date: 2024/10/1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlowBean implements Writable {

    private long upFlow;
    private long downFlow;
    private long sumFlow;

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);

    }

    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        downFlow = in.readLong();
        sumFlow = in.readLong();
    }

    public static FlowBean read(DataInput in) throws IOException {
        FlowBean w = new FlowBean();
        w.readFields(in);
        return w;
    }

    @Override
    public String toString() {
        return upFlow+ "\t" + downFlow  + "\t" + sumFlow;
    }

}
