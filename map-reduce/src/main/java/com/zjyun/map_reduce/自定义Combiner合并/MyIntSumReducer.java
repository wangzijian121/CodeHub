package com.zjyun.map_reduce.自定义Combiner合并;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MyIntSumReducer<Key> extends Reducer<Key, IntWritable, Key, IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Key key, Iterable<IntWritable> values,
                       Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get() * 2;
        }
        result.set(sum);
        context.write(key, result);
    }
}