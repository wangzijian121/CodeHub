package utils;

import java.util.concurrent.TimeUnit;

/**
 * @author zijian Wang
 */
public class SleepUtils {
    public static void sleep(long i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
