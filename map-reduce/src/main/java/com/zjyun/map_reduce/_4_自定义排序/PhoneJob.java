package com.zjyun.map_reduce._4_自定义排序;

import com.zjyun.map_reduce._3_自定义分区.PhonePartitioner;
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
 * @Date: 2024/10/2
 */
public class PhoneJob {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration configuration = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        //创建一个新的Job
        Job job = Job.getInstance(configuration);

        //通过查找给定类的来源来设置 Jar
        job.setJarByClass(PhoneJob.class);
        // Specify various job-specific parameters
        job.setJobName("王子健-手机号-排序-自定义分区MR");

        job.setMapperClass(PhoneMapper.class);
        job.setReducerClass(PhoneReducer.class);

        //map输入K,V类型
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        //reduce输出K,V类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        //提交任务，然后轮询进度，直至任务完成
        FileInputFormat.addInputPath(job, new Path("D:\\hadoop-input\\phone\\phone_data.txt"));
        long l = System.currentTimeMillis();
        System.out.println("time：" + l);
        FileOutputFormat.setOutputPath(job, new Path("D:\\hadoop-output\\" + l));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
