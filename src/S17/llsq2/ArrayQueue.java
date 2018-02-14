package S17.llsq2;

import java.util.*;

public class ArrayQueue {

    int tail;
    int head;
    int size;
    int[] data;

    public ArrayQueue(int size) {
        data = new int[size];
        tail = -1;
        head = -1;
        size = 0;
    }

    public int remove() {
        tail++;
        tail %= data.length;
        size--;
        return data[tail];
    }

    public void add(int val) {
        if (isFull()) {
            throw new RuntimeException();
        }
        head++;
        head %= data.length;
        data[head] = val;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(5);
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        q.remove();
        q.add(6);
        while (!q.isEmpty()) {
            System.out.println(q.remove());
        }
    }

}
