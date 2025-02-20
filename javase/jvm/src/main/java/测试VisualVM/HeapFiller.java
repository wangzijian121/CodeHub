package 测试VisualVM;


/***
 * 堆内存被字节数组对象填满
 * Run VM Options: -XX:+UseConcMarkSweepGC -Xloggc:D:\数据\GC.log -XX:+PrintGCDetails -XX:GCLogFileSize=100M -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -Xmx500m -Xms500m -Xmn200m -Xss1m
 */
public class HeapFiller {
    private static final int ONE_MB = 1024 * 1024*100;

    public static void main(String[] args) throws Exception {

/*        System.out.println(ONE_MB);
        List<byte[]> list = new ArrayList<>();
        int count = 0;
        try {
            while (true) {
                byte[] bytes = new byte[ONE_MB];
                list.add(bytes);
                count++;
                Thread.sleep(50);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("第 " + count + " 运行内存填满！");
            throw new Exception(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        Thread.sleep(10000000);
    }
}
/**
 * Java HotSpot(TM) 64-Bit Server VM (25.341-b10) for windows-amd64 JRE (1.8.0_341-b10), built on Jun 16 2022 15:51:07 by "java_re" with MS VC++ 15.9 (VS2017)
 * Memory: 4k page, physical 33361192k(12101152k free), swap 39914792k(13354672k free)
 * CommandLine flags: -XX:InitialHeapSize=524288000 -XX:MaxHeapSize=524288000 -XX:MaxNewSize=209715200 -XX:NewSize=209715200 -XX:+PrintGC -XX:+PrintGCTimeStamps -XX:ThreadStackSize=1024 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
 * 13.948: [GC (Allocation Failure)  152578K->142498K(486400K), 0.0139705 secs]
 */