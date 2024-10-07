package com.zjyun.map_reduce.自定义output输出规范;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/30
 */
public class MyWordCount {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        //远程提交时，需要指定本地的jar文件
        //configuration.set("mapreduce.job.jar","D:\\项目\\王子健-Java\\Java\\map-reduce\\target\\map-reduce-1.0-SNAPSHOT.jar");
        System.setProperty("HADOOP_USER_NAME", "root");
        //创建一个新的Job
        Job job = Job.getInstance(configuration);

        //通过查找给定类的来源来设置 Jar
        job.setJarByClass(MyWordCount.class);
        // Specify various job-specific parameters
        job.setJobName("王子健-WordCount");

        job.setMapperClass(WordCountMapper.class);
        //job.setReducerClass(WordCountReduce.class);


        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //提交任务，然后轮询进度，直至任务完成
        FileInputFormat.addInputPath(job, new Path("D:\\hadoop-input\\wordcount\\wang.txt"));

        job.setOutputFormatClass(MyFileOutputFormat.class);
        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop-output\\"+System.currentTimeMillis()));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
        //job.waitForCompletion(true);
    }
}
