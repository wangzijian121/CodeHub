package 多线程.java并发编程.java共享模型_无锁.ABA问题;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;


/**
 * 使用避免ABA问题的原子类并发
 *
 * @author zijian Wang
 */
public class AccountUsedAtomicMarkReference implements Account {

    private static AtomicMarkableReference<BigInteger> atomicMarkableReference =
            new AtomicMarkableReference<>(new BigInteger("10000"), false);

    /**
     * 获取余额
     *
     * @return
     */
    @Override
    public int getBalance() {
        return atomicMarkableReference.getReference().intValue();
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
            BigInteger snapShotValue = atomicMarkableReference.getReference();
            //获取版本标识
            boolean isMarked = atomicMarkableReference.isMarked();
            if (atomicMarkableReference.compareAndSet(snapShotValue, snapShotValue.subtract(new BigInteger(String.valueOf(amount))), isMarked, !isMarked)) {
                break;
            }
        }
    }
}
