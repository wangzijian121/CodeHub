package com.zjyun.map_reduce._10_ToolRunner;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @Description: 本地运行官方案例
 * @Author: Wang Zijian
 * @Date: 2024/9/30
 */
public class WordCountJob extends Configured implements Tool {

    public static void main(String[] args) throws Exception {
        int run = ToolRunner.run(new Configuration(), new WordCountJob(), args);
        System.exit(run);
    }

    @Override
    public int run(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        //远程提交时，需要指定本地的jar文件
        //configuration.set("mapreduce.job.jar","D:\\项目\\王子健-Java\\Java\\map-reduce\\target\\map-reduce-1.0-SNAPSHOT.jar");
        System.setProperty("HADOOP_USER_NAME", "root");
        //创建一个新的Job
        Job job = Job.getInstance(configuration);

        //通过查找给定类的来源来设置 Jar
        job.setJarByClass(WordCountJob.class);
        // Specify various job-specific parameters
        job.setJobName("王子健-WordCount");
        //
        job.setCombinerClass(IntSumReducer.class);
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        String inputPath = args[0];//"D:\\hadoop-input\\wordcount\\wang.txt"
        String outputPath = args[1];//"D:\\hadoop-output\\"
        //提交任务，然后轮询进度，直至任务完成
        FileInputFormat.addInputPath(job, new Path(inputPath));
        FileOutputFormat.setOutputPath(job, new Path(outputPath+System.currentTimeMillis()));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
        //job.waitForCompletion(true);
        return 0;
    }
}
