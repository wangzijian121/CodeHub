package d_队列queue.b_2链表实现;

/**
 * @author zijian Wang
 */

public class LinkedNode {

    private int value;
    private LinkedNode next;

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public LinkedNode getNext() {
        return next;
    }

    public LinkedNode(int x) {
        this.value = x;
    }

    public static void insert(LinkedNode leftNode, LinkedNode node) {
        LinkedNode rightNode = leftNode.next;
        node.next = rightNode;
        leftNode.next = node;
    }

    public static void delete(LinkedNode node) {
        //最后的节点防止空指针
        if (node.next == null) {
            return;
        }
        node.next = node.next.next;
    }

    public static int get(LinkedNode head, int length) {
        int index = 0;

        while (true) {
            if (length == index) {
                return head.value;
            }
            //最后的节点防止空指针
            if (head.next != null) {
                head = head.next;
                index++;
            } else {
                return -1;
            }
        }
    }

    private static void print(LinkedNode headNode) {
        while (headNode != null) {
            System.out.print(headNode.value + "\t");
            headNode = headNode.next;
        }
    }
}