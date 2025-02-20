package 消灭战舰游戏v1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    //数组
    private int[] nums = new int[7];

    //校验
    public String check(int point) {

        //是否击中
        if (nums[point] != 0 && nums[point] != -1) {
            nums[point] = -1;
            return "击中";
        } else {
            return "没击中";
        }
    }

    //
    public Boolean scan(int targetNum) {
        Boolean flag = true;

        // 雷达扫描 是否击沉
        for (int i = 0; i < (nums.length); i++) {
            // success flag
            if (nums[i] == targetNum) {
                //没有被击沉
                flag = false;
            }
        }
        return flag;
    }

    //开玩
    public void play() {

        //随机赋值
        Random random = new Random();
        int base = random.nextInt(nums.length - 2);
        //放置战舰
        for (int i = 0; i < 3; i++) {
            nums[base + i] = 1;
        }

        //开始游戏
        int count = 0;
        while (count < nums.length) {
            //作弊器
            //System.out.println("作弊器：" + Arrays.toString(nums));
            System.out.println("请输入攻击坐标:");
            Scanner scanner = new Scanner(System.in);
            int point = scanner.nextInt();
            //是否击中？
            String res = check(point);
            System.out.println(res);

            //击沉？
            if (scan(1)) {
                System.out.println("已经击沉了！");
                System.out.println("作弊器：" + Arrays.toString(nums));
                System.out.println("发射了" + count + "发导弹！");
                System.exit(0);
            } else {
                System.out.println("还未击沉！");
            }
            count++;
        }

    }

    public void setNums(int[] nums) {
        this.nums = nums;
    }
}
