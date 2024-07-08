package d_队列queue.b_2链表实现;


/**
 * 链表队列
 *
 * @author zijian Wang
 */
public class LinkedListQueue {

    /**
     * 基于链表实现
     */
    private LinkedNode header, tail;

    public LinkedListQueue() {
        this.header = null;
        this.tail = null;
    }

    public void push(int num) {

        LinkedNode linkedNode = new LinkedNode(num);
        if (header == null) {
            header = linkedNode;
            tail = linkedNode;
        } else {
            //当队列中有元素时
            tail.setNext(linkedNode);
            tail = linkedNode;
        }

    }

    public int pop() {
        //先进先出
        int headerValue =header.getValue();
        header=header.getNext();
        return headerValue;
    }

    private void print() {
        System.out.println("头：" + header.getValue());
        System.out.println("尾：" + tail.getValue());
        LinkedNode index=header;
        while (index != null) {
            System.out.print(index.getValue() + "\t");
            index = index.getNext();
        }
    }

    public static void main(String[] args) {
        LinkedListQueue linkedListQueue = new LinkedListQueue();
        linkedListQueue.push(1);
        linkedListQueue.push(2);
        linkedListQueue.push(3);
        linkedListQueue.print();
        System.out.println("push 4");
        linkedListQueue.push(4);
        linkedListQueue.print();
        //消费
        System.out.println("pop()出队列操作");
        linkedListQueue.pop();
        linkedListQueue.print();
    }
}
