package 多线程.java并发编程.练习.b_卖票练习;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * 售票窗口 1000张票
 * <p>
 * 游客：随机卖1~5张
 * 余票监控：
 * 售票余票大于等于随机数：售票
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.TicketWindow")
public class TicketWindow {
    private static long ticketCount = 5000L;
    private static long soldCount;


    public synchronized long getTicketCount() {
        return ticketCount;
    }

    public synchronized boolean ticketMonitorOnSell(int ticketSellCount) {

        if (getTicketCount() - ticketSellCount < 0) {
            return false;
        } else {
            return true;
        }

    }

    public synchronized void sell(int ticketSellCount) {
        //余票监控
        if (ticketMonitorOnSell(ticketSellCount)) {
            ticketCount -= ticketSellCount;
            soldCount += ticketSellCount;
            log.info("售出" + ticketSellCount + "张票！" + ",余票：" + getTicketCount()+",今日已售出："+soldCount);

        } else {
            log.info("余票：" + getTicketCount() + "余票不足！");
        }

    }

    public static void main(String[] args) throws InterruptedException {

        IntStream.range(1, 3).forEach(x -> {
            Thread thread = new Thread(() -> {
                TicketWindow ticketWindow = new TicketWindow();
                while (true) {
                    Visitor visitor = new Visitor();
                    int ticketSellCount = visitor.getNeedTicketCount();

                    ticketWindow.sell(ticketSellCount);
                    if (ticketWindow.getTicketCount() == 0) {
                        break;
                    }
                    try {
                        TimeUnit.MILLISECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }, "窗口" + x);
            thread.start();
        });
    }
}


class Visitor {
    private int needTicketCount;

    public int getNeedTicketCount() {
        Random random = new Random();
        this.needTicketCount = random.nextInt(5) + 1;
        return this.needTicketCount;
    }

}