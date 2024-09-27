package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;

/**
 *
 * @author Wang Zijian
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(configuration);
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            //迭代获得每个file元数据
            LocatedFileStatus file = files.next();
            //1. 获得文件路径
            Path path = file.getPath();
            System.out.println(path);
        }
    }
}