package c_栈_stack.数组实现;

import java.util.ArrayList;

/**
 * @author zijian Wang
 */
public class ArrayStack {

    private ArrayList<Integer> stack;

    public ArrayStack() {
        stack=new ArrayList<>();
    }


    public int push(int num) {
        stack.add(num);
        return num;
    }

    public int pop() {
        int num = stack.get((stack.size() - 1));
        stack.remove(stack.size() - 1);
        return num;
    }
    private  void print(){
        System.out.println(stack);
    }
    public static void main(String[] args) {

        ArrayStack s =new ArrayStack();
        s.push(111);
        s.push(222);
        s.push(333);
        s.print();
        //从尾部消费
        s.pop();
        s.print();
    }
}
