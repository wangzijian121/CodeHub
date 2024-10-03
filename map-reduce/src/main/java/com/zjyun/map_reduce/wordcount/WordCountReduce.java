package com.zjyun.map_reduce.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * (wangzijian,1)
 * (wangzijian,1)
 * (wang,1)
 * (wang123,1)
 * (lisi,1)
 * (lisi,1)
 * 对每行数据数据进行遍历
 */
public class WordCountReduce<Text> extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();
    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            System.out.println("val.get():" + val.get());
            sum += val.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}