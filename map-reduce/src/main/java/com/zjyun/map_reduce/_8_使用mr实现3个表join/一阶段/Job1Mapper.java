package com.zjyun.map_reduce._8_使用mr实现3个表join.一阶段;

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
public class Job1Mapper extends Mapper<LongWritable, Text, Text, BeanA> {

    private String targetFileName;


    @Override
    protected void setup(Mapper<LongWritable, Text, Text, BeanA>.Context context) throws InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        targetFileName = split.getPath().getName();
        System.out.println("处理" + targetFileName);
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, BeanA>.Context context) throws IOException, InterruptedException {
        BeanA beanA =new BeanA();
        if (targetFileName.equals("student.txt")) {
            String[] split = value.toString().split("\t");
            beanA.setSId(Integer.parseInt(split[0]));
            beanA.setSName(split[1]);
            beanA.setScId(-1);
            beanA.setSc_SId(Integer.parseInt(split[0]));
            beanA.setCId(-1);
            beanA.setTableName("student");

        } else if (targetFileName.equals("student_course.txt")) {
            String[] split = value.toString().split("\t");
            beanA.setSId(Integer.parseInt(split[1]));
            beanA.setSName("");
            beanA.setScId(Integer.parseInt(split[0]));
            beanA.setSc_SId(Integer.parseInt(split[1]));
            beanA.setCId(Integer.parseInt(split[2]));
            beanA.setTableName("student_course");
        }
        context.write(new Text(String.valueOf(beanA.getSc_SId())),beanA);
    }
}
