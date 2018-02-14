package S17.llsq2;

import java.util.*;

public class LRUCache {

    class Node {
        Node prev;
        Node next;
        String url;
        String data;
        public Node(String url, String data) {
            this.url = url;
            this.data = data;
            prev = null;
            next = null;
        }
    }

    Node dummyHead;
    Node dummyTail;
    int max_size;
    int size;
    Map<String, Node> map;


    public LRUCache(int size) {
        dummyHead = new Node(null, null);
        dummyTail = new Node(null, null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        max_size = size;
        this.size = 0;
        map = new HashMap<>();
    }

    public String getHtml(String url) {
        if (map.containsKey(url)) {
            moveToHead(map.get(url));
            return map.get(url).data;
        }
        String data = findOnline(url);
        addToHead(url, data);
        return data;
    }

    private String findOnline(String url) {
        System.out.println("you needed to go online to find: " + url);
        return "alsegjka;lkhsdf";
    }

    private void remove(Node toRemove) {
        Node prev = toRemove.prev;
        Node next = toRemove.next;

        toRemove.next = null;
        toRemove.prev = null;

        next.prev = prev;
        prev.next = next;
        size--;
        map.remove(toRemove.url);
    }

    private void removeFromTail() {
        if (!isEmpty()) {
            remove(dummyTail.prev);
        }
    }

    private void moveToHead(Node move) {
        remove(move);
        addToHead(move.url, move.data);
    }

    private void addToHead(String url, String data) {
        Node newNode = new Node(url, data);
        Node next = dummyHead.next;

        newNode.next = next;
        next.prev = newNode;

        dummyHead.next = newNode;
        newNode.prev = dummyHead;
        size++;
        map.put(url, newNode);

        if (size > max_size) {
            removeFromTail();
        }
    }

    private boolean isEmpty() {
        return dummyHead.next == dummyTail;
    }

    public static void main(String[] args) {
        LRUCache q = new LRUCache(2);
        q.getHtml("a");
        q.getHtml("b");
        q.getHtml("c");
        q.getHtml("a");
        q.getHtml("c");
    }

}
