package com.zjyun.map_reduce.自定义output输出规范;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/7
 */
public class MyFileOutputFormat extends FileOutputFormat<Text, IntWritable> {
    @Override
    public RecordWriter<Text, IntWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
        MyRecordWriter myRecordWriter = new MyRecordWriter(job);
        return myRecordWriter;
    }

    class MyRecordWriter extends RecordWriter<Text, IntWritable> {

        TaskAttemptContext job;
        FSDataOutputStream wang;
        FSDataOutputStream lisi;

        public MyRecordWriter(TaskAttemptContext job) throws IOException {
            this.job = job;
            FileSystem fs = FileSystem.get(job.getConfiguration());
            wang = fs.create(new Path("D:\\hadoop-output\\wang.txt"));
            lisi = fs.create(new Path("D:\\hadoop-output\\lisi.txt"));
        }

        @Override
        public void write(Text key, IntWritable value) throws IOException, InterruptedException {
            if (key.toString().contains("wang")) {
                wang.write((key + "\t" + value.toString() + "\n").getBytes());
            } else if (key.toString().contains("lisi")) {
                lisi.write((key + "\t" + value.toString() + "\n").getBytes());
            }
        }

        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            wang.close();
            lisi.close();
        }
    }
}
