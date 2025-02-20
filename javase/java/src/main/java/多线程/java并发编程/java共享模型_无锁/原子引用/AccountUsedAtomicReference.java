package 多线程.java并发编程.java共享模型_无锁.原子引用;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicReference;


/**
 * 使用原子类的并发
 *
 * @author zijian Wang
 */
public class AccountUsedAtomicReference implements Account {

    private static AtomicReference<BigInteger> atomicReference = new AtomicReference<>(new BigInteger("100000"));

    /**
     * 获取余额
     *
     * @return
     */
    @Override
    public int getBalance() {
        return atomicReference.get().intValue();
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
            BigInteger snapShotValue = atomicReference.get();

            if (atomicReference.compareAndSet(snapShotValue, snapShotValue.subtract(new BigInteger(String.valueOf(amount))))) {
                break;
            }
        }
    }
}
