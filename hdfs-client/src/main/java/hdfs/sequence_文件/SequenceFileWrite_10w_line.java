package hdfs.sequence_文件;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/28
 */
public class SequenceFileWrite_10w_line {

    public void read() throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(configuration);

        SequenceFile.Reader reader = new SequenceFile.Reader(fs, new Path("/sequence.seq"), configuration);
        Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), configuration);
        Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), configuration);
        while (reader.next(key, value)) {
            System.out.printf("%s\t%s\n", key, value);
        }
        reader.close();
    }

    public void write() throws IOException {
        Configuration configuration = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        //System.setProperty("os.name", "XXX");
        SequenceFile.Writer.Option file = SequenceFile.Writer.file(new Path("/sequence.seq"));
        SequenceFile.Writer.Option keyClass = SequenceFile.Writer.keyClass(IntWritable.class);
        SequenceFile.Writer.Option valueClass = SequenceFile.Writer.valueClass(Text.class);
        SequenceFile.Writer writer = SequenceFile.createWriter(configuration, file, keyClass, valueClass);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            IntWritable intWritable = new IntWritable(i);
            writer.append(intWritable, new Text());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        SequenceFileWrite_10w_line demo = new SequenceFileWrite_10w_line();
        //demo.write();//写到HDFS上
        demo.read();//读取
    }
}