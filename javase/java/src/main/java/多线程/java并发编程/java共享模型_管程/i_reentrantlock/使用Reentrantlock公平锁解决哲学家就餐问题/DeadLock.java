package 多线程.java并发编程.java共享模型_管程.i_reentrantlock.使用Reentrantlock公平锁解决哲学家就餐问题;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 使用ReentrantLock 解决《哲学家就餐问题》。
 * <p>
 * 5个哲学家 5只筷子 一起吃饭，需要2个筷子,会发生死锁：
 * 18:36:37 [main] c.People - 哲学家1👨拿到了1和2
 * 18:36:37 [main] c.People - 哲学家2👨拿到了2和3
 * 18:36:37 [main] c.People - 哲学家3👨拿到了3和4
 * 18:36:37 [main] c.People - 哲学家4👨拿到了4和5
 * 18:36:37 [main] c.People - 哲学家5👨拿到了5和1
 * <p>
 * ---------------------------------------------
 * 思路：通过ReentrantLock 的tryLock内置公平锁获取锁，如果没有获取到餐具，将解锁，让给其他人，这样就不会出现死锁现象。
 *
 * @author zijian Wang
 *
 */
@Slf4j(topic = "c.DeadLock")
public class DeadLock {

    public static void main(String[] args) throws InterruptedException {

        Table table = new Table(5, 5);
        //哲学家
        List<People> peopleList = table.getPeopleList();
        //餐具
        List<Tableware> tablewareList = table.getTablewareList();
        peopleList.stream().forEach(x -> {
            int i = peopleList.indexOf(x);
            //拿到餐具
            x.pickUpTableware(tablewareList.get(i % peopleList.size())
                    , tablewareList.get((i + 1) % peopleList.size()));
            x.start();
        });
    }
}

@Slf4j(topic = "c.People")
@Data
@AllArgsConstructor
class People extends Thread {

    private String peopleName;
    private Tableware left;
    private Tableware right;

    public People(String peopleName) {
        this.peopleName = peopleName;
    }


    public void pickUpTableware(Tableware left, Tableware right) {
        this.left = left;
        this.right = right;
        log.info(peopleName + "👨拿到了" + left.getId() + "和" + right.getId());
    }

    @Override
    public void run() {
        try {
            eat();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void eat() throws InterruptedException {
        for (; ; ) {
            if (getTableware(left, right)) {
                log.info(peopleName + "正在吃饭🍕...");
                Thread.sleep(1000);
                left.unlock();
                right.unlock();
            }
//            不能将unlock放在if条件外，因为有些人会获取不到餐具，导致java.lang.IllegalMonitorStateException
//            left.unlock();
//            right.unlock();
        }
    }

    private boolean getTableware(Tableware left, Tableware right) {
        if (left.tryLock()) {
            if (right.tryLock()) {
                return true;
            } else {
                left.unlock();
                return false;
            }
        } else {
            return false;
        }
    }
}

/**
 * 餐具：筷子
 */
@Slf4j(topic = "c.Tableware")
@Data
@AllArgsConstructor
class Tableware extends ReentrantLock {
    private int id;
}

/**
 * 桌子
 */
@Slf4j(topic = "c.Table")
@Data
class Table {

    private List<Tableware> tablewareList = new ArrayList<>();
    private List<People> peopleList = new ArrayList<>();

    public Table(int tableCount, int peopleCount) {

        IntStream.range(1, tableCount + 1).forEach(x -> tablewareList.add(new Tableware(x)));

        IntStream.range(1, peopleCount + 1).forEach(x -> peopleList.add(new People("哲学家" + x)));
    }

    public List<Tableware> getTablewareList() {
        return tablewareList;
    }

    public List<People> getPeopleList() {
        return peopleList;
    }
}

