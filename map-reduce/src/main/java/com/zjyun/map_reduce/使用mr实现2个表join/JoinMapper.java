package com.zjyun.map_reduce.使用mr实现2个表join;

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
public class JoinMapper extends Mapper<LongWritable, Text, Text, StudentAndCourseJoinTableBean> {

    private String targetFileName;


    @Override
    protected void setup(Mapper<LongWritable, Text, Text, StudentAndCourseJoinTableBean>.Context context) throws InterruptedException {
        FileSplit split = (FileSplit) context.getInputSplit();
        targetFileName = split.getPath().getName();
        System.out.println("处理" + targetFileName);
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, StudentAndCourseJoinTableBean>.Context context) throws IOException, InterruptedException {
        StudentAndCourseJoinTableBean bean = new StudentAndCourseJoinTableBean();
        if (targetFileName.equals("student.txt")) {
            String[] split = value.toString().split("\t");
            bean.setSId(Integer.parseInt(split[0]));
            bean.setSName(split[1]);
            bean.setCId(Integer.parseInt(split[2]));
            bean.setTableName("student");
        } else if (targetFileName.equals("course.txt")) {
            String[] split = value.toString().split("\t");
            bean.setCId(Integer.parseInt(split[0]));
            bean.setCName(split[1]);
            bean.setTableName("course");
        }
        context.write(new Text(String.valueOf(bean.getCId())),bean);
    }
}
