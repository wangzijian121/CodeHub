package 多线程.java并发编程.java共享模型_无锁.无锁;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类的并发
 *
 * @author zijian Wang
 */
public class AccountUsedAtomic implements Account {

    private static AtomicInteger atomicInteger = new AtomicInteger(10000);

    /**
     * 获取余额
     *
     * @return
     */
    @Override
    public int getBalance() {
        return 0;
    }

    /**
     * 取款
     *
     * @param amount 取款金额
     */
    @Override
    public void withdrawals(int amount) {
        //CAS A ->B
        while (true) {
            int snapShot = atomicInteger.get();
            if (atomicInteger.compareAndSet(snapShot,amount-10)) {
                break;
            }
        }
        //使用原子类自带的方法
//        atomicInteger.getAndAdd(-1 * amount);
    }
}
