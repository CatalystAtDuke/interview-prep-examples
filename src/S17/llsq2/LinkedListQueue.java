package S17.llsq2;

public class LinkedListQueue {

    class Node {
        Node prev;
        Node next;
        int val;
        public Node(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }

    Node dummyHead;
    Node dummyTail;

    public LinkedListQueue() {
        dummyHead = new Node(0);
        dummyTail = new Node(0);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public int remove() {
        Node toRemove = dummyTail.prev;
        Node prev = toRemove.prev;

        toRemove.next = null;
        toRemove.prev = null;

        dummyTail.prev = prev;
        prev.next = dummyTail;

        return toRemove.val;
    }

    public void add(int val) {
        Node newNode = new Node(val);
        Node next = dummyHead.next;

        newNode.next = next;
        next.prev = newNode;

        dummyHead.next = newNode;
        newNode.prev = dummyHead;
    }

    public boolean isEmpty() {
        return dummyHead.next == dummyTail;
    }

    public static void main(String[] args) {
        LinkedListQueue q = new LinkedListQueue();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        while (!q.isEmpty()) {
            System.out.println(q.remove());
        }
    }

}
