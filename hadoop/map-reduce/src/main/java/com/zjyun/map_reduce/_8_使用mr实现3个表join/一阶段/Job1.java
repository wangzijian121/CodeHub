package com.zjyun.map_reduce._8_使用mr实现3个表join.一阶段;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/5
 */
public class Job1 {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration configuration = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        //创建一个新的Job
        Job job = Job.getInstance(configuration);

        //通过查找给定类的来源来设置 Jar
        job.setJarByClass(Job1.class);
        // Specify various job-specific parameters
        job.setJobName("王子健-使用MR实现3个表的Join");

        job.setMapperClass(Job1Mapper.class);
        //job.setReducerClass(Job1Reducer.class);

        //map输入K,V类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BeanA.class);
        //reduce输出K,V类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //提交任务，然后轮询进度，直至任务完成
        //TODO 替换为目录
        FileInputFormat.addInputPath(job, new Path("D:\\hadoop-input\\demo3表\\student.txt"));
        FileInputFormat.addInputPath(job, new Path("D:\\hadoop-input\\demo3表\\student_course.txt"));

        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop-input\\demo3表\\temp"));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}