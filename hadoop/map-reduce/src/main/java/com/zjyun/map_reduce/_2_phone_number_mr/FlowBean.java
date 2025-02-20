package com.zjyun.map_reduce._2_phone_number_mr;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.hadoop.io.Writable;

import java.io.*;

/**
 * @Description: 自定义序列化类
 * @Author: Wang ZiJian
 * @Date: 2024/10/1
 */
@Getter
@Setter
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
        return downFlow + "\t" + upFlow + "\t" + sumFlow;
    }

    /**
     * 测试序列化
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        FlowBean flowBean = new FlowBean();
        flowBean.setUpFlow(100);
        flowBean.setDownFlow(100);
        flowBean.setSumFlow(200);
        OutputStream outputStream = new FileOutputStream(new File("D://wang.txt"));
        flowBean.write(new DataOutputStream(outputStream));

        FlowBean read = flowBean.read(new DataInputStream(new FileInputStream(new File("D://wang.txt"))));
        System.out.println(read);
    }
}
