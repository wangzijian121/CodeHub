package hdfs.sequence_文件;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Description:
 * @Author: Wang ZiJian
 * @Date: 2024/10/28
 */
public class SequenceFileDemo {

    public void read() {

    }

    public void write() throws IOException {
        Configuration configuration = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        //System.setProperty("os.name", "XXX");

        SequenceFile.Writer.Option file  = SequenceFile.Writer.file(new Path("hdfs://mycluster/sequence.seq"));

        SequenceFile.Writer.Option keyClass = SequenceFile.Writer.keyClass(IntWritable.class);
        SequenceFile.Writer.Option valueClass = SequenceFile.Writer.valueClass(Text.class);

        SequenceFile.Writer writer = SequenceFile.createWriter(configuration, file, keyClass, valueClass);
        for (int i = 0; i < 100000; i++) {
            IntWritable intWritable = new IntWritable(i);
            writer.append(intWritable, new Text("hello" + i));
        }
        writer.close();
    }

    public static void main(String[] args) throws IOException {
        SequenceFileDemo demo = new SequenceFileDemo();
        demo.write();
    }
}