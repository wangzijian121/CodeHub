package c_栈_stack.链表实现;

/**
 * 使用链表实现栈
 *
 * @author zijian Wang
 */
public class LinkedListStack {

    private LinkedNode linkedTop;
    private int stkSize = 0;

    public LinkedListStack() {
        linkedTop = null;
    }

    public void push(LinkedNode node) {
        if (linkedTop == null) {
            linkedTop = node;
        } else {
            node.setNext(linkedTop);
            linkedTop =node;
        }
        stkSize++;
    }

    public int  pop() {
        if(linkedTop !=null){
            int element= linkedTop.getValue();
            linkedTop = linkedTop.getNext();
            return element;
        }else{
            return  -1;
        }
    }

    private void print() {
        while (linkedTop != null) {
            System.out.print(linkedTop.getValue() + "->");
            linkedTop = linkedTop.getNext();

        }
    }

    public static void main(String[] args) {
        //init
        LinkedListStack linkedListStack = new LinkedListStack();
        LinkedNode node1 = new LinkedNode(1);
        LinkedNode node2 = new LinkedNode(2);
        LinkedNode node3 = new LinkedNode(3);
        LinkedNode node4 = new LinkedNode(4);

        //push
        linkedListStack.push(node1);
        linkedListStack.push(node2);
        linkedListStack.push(node3);
        linkedListStack.push(node4);
        System.out.println();
        //pop
        System.out.println("消费了："+linkedListStack.pop());
        linkedListStack.print();
    }

}
