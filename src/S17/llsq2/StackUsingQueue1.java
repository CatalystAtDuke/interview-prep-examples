package S17.llsq2;

import java.util.*;

class StackUsingQueue1 {
        
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private boolean q1Main;

    public StackUsingQueue1() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
        q1Main = true;
    }

    public void push(int x) {
        Queue<Integer> main;
        Queue<Integer> alt;
        if (q1Main) {
            main = q1;
            alt = q2;
        } else {
            main = q2;
            alt = q1;
        }
        alt.add(x);
        while (!main.isEmpty()) {                
            alt.add(main.remove());
        }
        q1Main = !q1Main;
    }

    public void pop() {
        Queue<Integer> main = q1Main ? q1 : q2;
        main.remove();
    }

    public boolean empty() {
        Queue<Integer> main = q1Main ? q1 : q2;
        return main.isEmpty();
    }

    public int top() {
        Queue<Integer> main = q1Main ? q1 : q2;;
        return main.peek();
    }
}
