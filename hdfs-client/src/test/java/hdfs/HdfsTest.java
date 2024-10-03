package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

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
        fileSystem.close();
    }

    /**
     * 上传文件
     */
    @Test
    public void uploadFile() throws IOException {
        fileSystem.moveFromLocalFile(new Path("D://456.txt"), new Path("/"));
        fileSystem.close();
    }

    /**
     * 删除文件
     *
     * @throws IOException
     */
    @Test
    public void deleteFile() throws IOException {
        fileSystem.delete(new Path("/123.txt"), true);
        fileSystem.close();
    }


    /**
     * 移动文件
     *
     * @throws IOException
     */
    @Test
    public void moveFile() throws IOException {
        fileSystem.rename(new Path("/wang121.txt"), new Path("/wangzijian_rename.txt"));
        fileSystem.close();
    }

    /**
     * 查看文件内容
     *
     * @throws IOException
     */
    @Test
    public void catFile() throws IOException {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/wangzijian_rename.txt"), false);
        while (files.hasNext()) {
            LocatedFileStatus fileStatus = files.next();
            System.out.println("========" + fileStatus.getPath() + "=========");
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getGroup());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());

            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            String context = Arrays.toString(blockLocations);
            System.out.println(context);
        }
        fileSystem.close();
    }

    /**
     * 判断指定的是文件夹还是文件
     *
     * @throws IOException
     */
    @Test
    public void isFile() throws IOException {
        System.out.println("文件：" + fileSystem.isFile(new Path("/wangzijian_rename.txt")));
        System.out.println("文件夹：" + fileSystem.isDirectory(new Path("/wangzijian_rename.txt")));
        fileSystem.close();
    }
}