package 多线程.java并发编程.java共享模型_无锁.无锁;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author zijian Wang
 */
public class Main {

/*    public static void main(String[] args) throws InterruptedException {
        Account account = new AccountNothing();
        System.out.println("初始金额："+account.getBalance());
        IntStream.range(0, 1000).forEach(x -> {
            Thread thread = new Thread(() -> {
                account.withdrawals(10);
            }, "线程" + x);
            thread.start();
        });
        TimeUnit.SECONDS.sleep(3);
        System.out.println("最后的余额：" + account.getBalance());
    }*/

/*    public static void main(String[] args) throws InterruptedException {
        AccountUsedLock accountUsedLock = new AccountUsedLock();

        IntStream.range(0, 1000).forEach(x -> {
            Thread thread = new Thread(() -> {
                accountUsedLock.withdrawals(10);
            }, "线程" + x);
            thread.start();
        });
        TimeUnit.SECONDS.sleep(3);
        System.out.println("最后的余额：" + accountUsedLock.getBalance());
    }*/


    public static void main(String[] args) throws InterruptedException {
        Account account = new AccountUsedAtomic();

        IntStream.range(0, 1000).forEach(x -> {
            Thread thread = new Thread(() -> {
                account.withdrawals(10);
            }, "线程" + x);
            thread.start();
        });
        TimeUnit.SECONDS.sleep(2);
        System.out.println("最后的余额：" + account.getBalance());
    }
}
