package com.zjyun.map_reduce._8_使用mr实现3个表join.二阶段;

import com.zjyun.map_reduce._8_使用mr实现3个表join.一阶段.BeanB;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/5
 */
public class Job2Mapper extends Mapper<LongWritable, Text, Text, BeanB> {

    private String targetFileName;

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, BeanB>.Context context)  {
        FileSplit split = (FileSplit) context.getInputSplit();
        targetFileName = split.getPath().getName();
        System.out.println("处理" + targetFileName);
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, BeanB>.Context context) throws IOException, InterruptedException {
        BeanB beanB = new BeanB();
        if (targetFileName.equals("part-r-00000")) {
            String[] split = value.toString().split("\t");

            beanB.setSId(Integer.parseInt(split[0]));
            beanB.setSName(split[2]);
            beanB.setCId(Integer.parseInt(split[5]));
            beanB.setTableName("part");

        } else if (targetFileName.equals("course.txt")) {
            String[] split = value.toString().split("\t");
            beanB.setSId(-1);
            beanB.setCId(Integer.parseInt(split[0]));
            beanB.setCName(split[1]);
            beanB.setTableName("course");
        }
        context.write(new Text(String.valueOf(beanB.getCId())),beanB);
    }
}
