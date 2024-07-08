package a_数组array.数组;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据结构-数组
 *
 * @author zijian Wang
 */
@Slf4j(topic = "c.MyArray")
public class MyArray {

    private int[] arr;

    public MyArray(int size) {
        arr = new int[size];
    }

    public int get(int index) {
        return arr[index];
    }

    public void delete(int index) {
        //删除后，后面的元素往前移1位
        for (int i = index; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[arr.length - 1] = 0;
    }

    public void insert(int num, int index) {

        //插入元素位置往后移动1位
        for (int i = arr.length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = num;
    }

    public int indexOf(int num) {
        //遍历值返回索引
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }

    private void print() {
        for (int i = 0; i < arr.length; i++) {
            log.info("{}\t", arr[i]);
        }
    }

    public static void main(String[] args) {

        MyArray myArray = new MyArray(5);
        myArray.insert(111, 0);
        myArray.insert(333, 1);
        myArray.insert(444, 3);
        myArray.insert(555, 4);
        myArray.insert(666, 4);
        myArray.print();
        log.info("获取第4个元素:{}", myArray.get(4));
        myArray.delete(1);
        myArray.print();

        log.info("898的索引（没有这个元素 返回-1） ：{}", myArray.indexOf(898));
        log.info("666的索引（有这个元素返回索引） ：{}", myArray.indexOf(666));
    }
}
