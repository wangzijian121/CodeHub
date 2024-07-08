package b_链表_linkedlist;

/**
 * double-ended Linked List
 *
 * @author zijian Wang
 */
public class DeLinkedNode {

    private int value;
    private DeLinkedNode next;
    private DeLinkedNode prev;

    public DeLinkedNode(int value) {
        this.value = value;
        next = prev = null;
    }

    public void insert(DeLinkedNode newNode) {

        //插入到调用的节点后面的节点，暂存为node
        DeLinkedNode node = this.next;
        this.next = newNode;
        newNode.next = node;
        newNode.prev = this;
    }

    public static void delete(DeLinkedNode deleteNode) {

        //将要删除的节点暂存为prevNode 、nextNode
        DeLinkedNode prevNode = deleteNode.prev;
        DeLinkedNode nextNode = deleteNode.next;

        //关联前后的节点
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }


    private static void print(DeLinkedNode headNode) {
        while (headNode != null) {
            System.out.print(headNode.value + "\t");
            headNode = headNode.next;
        }
    }

    public static void main(String[] args) {

        DeLinkedNode deLinkedNode1 = new DeLinkedNode(1);
        DeLinkedNode deLinkedNode2 = new DeLinkedNode(2);
        DeLinkedNode deLinkedNode3 = new DeLinkedNode(3);

        deLinkedNode1.insert(deLinkedNode2);
        deLinkedNode2.insert(deLinkedNode3);


        print(deLinkedNode1);

        System.out.println();
        System.out.println("deLinkedNode 1的前驱：" + deLinkedNode1.prev);
        System.out.println("deLinkedNode 1的后继：" + deLinkedNode1.next.value);

        System.out.println("deLinkedNode 2的前驱：" + deLinkedNode2.prev.value);
        System.out.println("deLinkedNode 2的后继：" + deLinkedNode2.next.value);

        System.out.println("deLinkedNode 3的前驱：" + deLinkedNode3.prev.value);
        System.out.println("deLinkedNode 3的后继：" + deLinkedNode3.next);

        //删除
        delete(deLinkedNode2);
        System.out.println("删除节点2后");
        print(deLinkedNode1);
        System.out.println();
        System.out.println("deLinkedNode 1的前驱：" + deLinkedNode1.prev);
        System.out.println("deLinkedNode 1的后继：" + deLinkedNode1.next.value);

        System.out.println("deLinkedNode 3的前驱：" + deLinkedNode3.prev.value);
        System.out.println("deLinkedNode 3的后继：" + deLinkedNode3.next);

    }
}
