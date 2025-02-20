package 多线程.java并发编程.java共享模型_无锁.ABA问题;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicStampedReference;


/**
 * 使用避免ABA问题的原子类并发
 *
 * @author zijian Wang
 */
public class AccountUsedAtomicSampledReference implements Account {

    private static AtomicStampedReference<BigInteger> atomicStampedReference = new AtomicStampedReference<>(new BigInteger("100000"), 0);

    /**
     * 获取余额
     *
     * @return
     */
    @Override
    public int getBalance() {
        return atomicStampedReference.getReference().intValue();
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
            //获取值
            BigInteger snapShotValue = atomicStampedReference.getReference();
            //获取版本标识
            int stamp = atomicStampedReference.getStamp();
            if (atomicStampedReference.compareAndSet(snapShotValue, new BigInteger(String.valueOf(amount + 1)), stamp, stamp + 1)) {
                break;
            }
        }
    }
}
