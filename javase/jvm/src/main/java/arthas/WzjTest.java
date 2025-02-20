package arthas;

public class WzjTest {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("当前时间：" + getNowTime());
            Thread.sleep(5000);
        }
    }

    public static long getNowTime() {
        return System.currentTimeMillis();
    }
}
