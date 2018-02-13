package S17.llsq;

public class LinkedListProblem {

    static class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /* Helper function.
     * Will enter infinite loop on list with cycles
     */

    public static void printList(Node head) {
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.val + " -> ");
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    /* Helper function.
     * Will enter infinite loop on list with cycles
     */
    public static int length(Node head) {
        Node curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /*
     * Will enter infinite loop on list with cycles
     */
    public static Node middle(Node head) {
        if (head == null) return head;
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /*
     * Assumes that ll1 and ll2 actually DO intersect
     */

    public static Node intersect (Node ll1, Node ll2) {
        int length1 = length(ll1);
        int length2 = length(ll2);
        int forward = Math.abs(length1-length2);
        Node curr1 = ll1;
        Node curr2 = ll2;
        for (int i = 0; i<forward; i++) {
            if (length1 > length2) curr1 = curr1.next;
            else curr2 = curr2.next;
        }
        while (curr1 != curr2) {
            curr1 = curr1.next;
            curr2 = curr2.next;
        }
        return curr1;
    }

    public static Node cycle(Node head) {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (slow != fast) {
            return null;
        }
        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static Node add (Node n1, Node n2) {
        n1 = reverse(n1);
        n2 = reverse(n2);
        Node c1 = n1;
        Node c2 = n2;
        Node dummy = new Node(0);
        Node c3 = dummy;
        int overflow = 0;
        while (c1 != null || c2 != null) {
            int sum = (c1 == null ? 0 : c1.val) + (c2 == null ? 0 : c2.val) + overflow;
            overflow = sum/10;
            Node next = new Node(sum % 10);
            c3.next = next;
            c3 = next;
            c1 = c1.next;
            c2 = c2.next;
        }

        if (overflow > 0) {
            c3.next = new Node(overflow);
        }

        n1 = reverse(n1);
        n2 = reverse(n2);

        return reverse(dummy.next);
    }

    public static void midTest() {
        System.out.println("Testing middle function");
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        printList(a);
        System.out.println(middle(a).val);

    }

    public static void reverseTest() {
        System.out.println("Testing reverse function");
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        printList(a);
        printList(reverse(a));
    }

    public static void intersectTest() {
        System.out.println("Testing intersect function");
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        Node f = new Node(-1);
        Node g = new Node(-2);
        f.next = g;
        g.next = d;
        printList(a);
        printList(f);
        System.out.println(intersect(a,f).val);
    }

    public static void cycleTest() {
        System.out.println("Testing cycle function");
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(6);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = c;
        Node res = cycle(a);
        System.out.println(res == null ? res : res.val);
    }

    public static void addTest() {
        System.out.println("Testing add function");
        Node a = new Node(2);
        Node b = new Node(3);
        Node c = new Node(5);
        Node d = new Node(9);
        Node e = new Node(2);
        Node f = new Node(7);
        a.next = b;
        b.next = c;

        d.next = e;
        e.next = f;
        printList(a);
        printList(d);

        Node res = add(a,d);
        printList(res);
    }


    public static void main(String[] args) {
        midTest();
        reverseTest();
        intersectTest();
        cycleTest();
        addTest();
    }

}
