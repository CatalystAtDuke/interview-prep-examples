package S17.llsq2;

import java.util.*;

class StackUsingQueue3 {

    private Queue<Integer> q;

    public StackUsingQueue3() {
        q = new LinkedList<>();
    }

    public void push(int x) {
        int oldSize = q.size();
        q.add(x);
        for (int i = 0; i<oldSize; i++) {              
            q.add(q.remove());
        }
    }

    public void pop() {
        q.remove();
    }

    public boolean empty() {
        return q.isEmpty();
    }

    public int top() {
        return q.peek();
    }

}