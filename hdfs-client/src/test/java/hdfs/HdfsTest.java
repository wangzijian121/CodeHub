package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Test;

import java.io.IOException;

/**
 * @Description:
 * @Author: Wang Zijian
 * @Date: 2024/9/27
 */
public class HdfsTest {
    private FileSystem fileSystem;

    {
        System.setProperty("HADOOP_USER_NAME", "root");
        System.setProperty("HADOOP_HOME", "D:\\hadoop-3.4.0");
        System.setProperty("hadoop.home.dir", "D:\\hadoop-3.4.0");
        Configuration configuration = new Configuration();

        try {
            fileSystem = FileSystem.get(configuration);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 递归查看文件列表
     *
     * @throws IOException
     */
    @Test
    public void testList() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            //迭代获得每个file元数据
            LocatedFileStatus file = files.next();
            //1. 获得文件路径
            Path path = file.getPath();
            System.out.println(path);
        }
    }

    /**
     * 下载文件
     *
     * @throws IOException
     */
    @Test
    public void downLoad() throws IOException {
        fileSystem.moveToLocalFile(new Path("/wangzijian.txt"), new Path("D://"));
    }

    /**
     * 上传文件
     */
    @Test
    public void uploadFile() throws IOException {
        fileSystem.moveFromLocalFile(new Path("D://123.txt"), new Path("/"));
    }

    /**
     * 删除文件
     * @throws IOException
     */
    @Test
    public void deleteFile() throws IOException {
        fileSystem.delete(new Path("/123.txt"), true);
    }
}
