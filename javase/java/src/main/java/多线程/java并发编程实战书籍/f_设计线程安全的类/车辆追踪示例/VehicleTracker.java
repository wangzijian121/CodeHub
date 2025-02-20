package 多线程.java并发编程实战书籍.f_设计线程安全的类.车辆追踪示例;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.NotThreadSafe;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用java监视器模式实现的车辆追踪器
 * 一个线程负责更新汽车的位置。
 * 另一个线程负责读取汽车的位置。
 *
 * @author zijian Wang
 */
@ThreadSafe
public class VehicleTracker {
    @GuardedBy("this")
    private Map<String, Point> pointMap;

    public VehicleTracker(Map<String, Point> pointMap) {
        pointMap = new HashMap<>();
        this.pointMap = pointMap;
    }
    //GPS生成汽车的位置

    //获取所有汽车的位置
    //查询指定汽车的位置


}

@NotThreadSafe
class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }
}

class Gps {

}
