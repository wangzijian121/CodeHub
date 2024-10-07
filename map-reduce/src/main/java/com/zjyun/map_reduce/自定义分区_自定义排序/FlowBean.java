package com.zjyun.map_reduce.自定义分区_自定义排序;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class FlowBean implements WritableComparable<FlowBean> {

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

    /**
     * 自定义规则，自己定义对象之间的大小靠什么比较
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(FlowBean o) {
        if (this.sumFlow > o.sumFlow) {
            return 1;
        } else if (this.sumFlow < o.sumFlow) {
            return -1;
        } else if (this.upFlow == o.upFlow) {
            //总流量一样大时按照上行流量比较
            if (this.upFlow > o.upFlow) {
                return 1;
            } else if (this.upFlow < o.upFlow) {
                return -1;
            } else {
                return 0;
            }
        }
        return -1;
    }
}
