package S17.llsq2;

import java.util.*;

class StackUsingQueue2 {

    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public StackUsingQueue2() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q2.add(x);
        while (!q1.isEmpty()) {                
            q2.add(q1.remove());
        }
        swap();
    }

    private void swap() {
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public void pop() {
        q1.remove();
    }

    public boolean empty() {
        return q1.isEmpty();
    }

    public int top() {
        return q1.peek();
    }

}