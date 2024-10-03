package com.zjyun.map_reduce.phone_number_mr;

import com.zjyun.map_reduce.wordcount.WordCountMapper;
import com.zjyun.map_reduce.wordcount.WordCountReduce;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.util.Date;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/2
 */
public class PhoneNumberDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        //远程提交时，需要指定本地的jar文件
        //configuration.set("mapreduce.job.jar","D:\\项目\\王子健-Java\\Java\\map-reduce\\target\\map-reduce-1.0-SNAPSHOT.jar");
        System.setProperty("HADOOP_USER_NAME", "root");
        //创建一个新的Job
        Job job = Job.getInstance(configuration);

        //通过查找给定类的来源来设置 Jar
        job.setJarByClass(PhoneNumberDriver.class);
        // Specify various job-specific parameters
        job.setJobName("王子健-手机号MR");

        job.setMapperClass(PhoneNumberMapper.class);
        job.setReducerClass(PhoneNumberReducer.class);
        //map输入K,V类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        //reduce输出K,V类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //提交任务，然后轮询进度，直至任务完成
        FileInputFormat.addInputPath(job, new Path("D:\\hadoop-input\\phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop-output\\"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
