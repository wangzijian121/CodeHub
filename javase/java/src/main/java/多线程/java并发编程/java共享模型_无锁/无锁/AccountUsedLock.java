package 多线程.java并发编程.java共享模型_无锁.无锁;

import javax.annotation.concurrent.ThreadSafe;

/**
 * 使用锁实现
 *
 * @author zijian Wang
 */
@ThreadSafe
public class AccountUsedLock implements Account {
    private static int balance = 10000;

    /**
     * 获取余额
     *
     * @return
     */
    @Override
    public int getBalance() {
        synchronized (this) {
            return balance;
        }
    }

    /**
     * 取款
     *
     * @param amount 取款金额
     */
    @Override
    public void withdrawals(int amount) {
        synchronized (this) {
            balance -= amount;
        }
    }
}
