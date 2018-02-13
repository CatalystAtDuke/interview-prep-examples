package S17.llsq;

import java.util.*;

public class MaxStack {

    private Stack<Integer> values;
    private Stack<Integer> maxes;

    public MaxStack() {
        values = new Stack<>();
        maxes = new Stack<>();
    }

    public void push(int val) {
        if (val >= getMax()) {
            maxes.push(val);
        }
        values.push(val);
    }

    public int pop() {
        if (values.peek() == getMax()) {
            maxes.pop();
        }
        return values.pop();
    }

    public int getMax() {
        return isEmpty() ? Integer.MIN_VALUE : maxes.peek();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public static void testMaxStack() {
        MaxStack m = new MaxStack();
        System.out.println("Testing max stack");
        int[] test = new int[]{1,2,6,3,10};
        System.out.println(Arrays.toString(test));
        System.out.println("max values while pushing");
        for (int i : test) {
            m.push(i);
            System.out.println(m.getMax());
        }
        System.out.println("max values while popping");
        while (!m.isEmpty()) {
            m.pop();
            System.out.println(m.getMax());
        }
    }

    public static void main(String[] args) {
        testMaxStack();
    }

}
