package com.example.linked;

import java.util.HashMap;

/**
 * Created by AckywOw on 2016/7/31.
 */
public class LRUCache {

    private class Node {
        Node prev;
        Node next;
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map = new HashMap<>();
    private Node head = new Node(-1, -1);
    private Node tail = new Node(-1, -1);

    // @param capacity, an integer
    public LRUCache(int capacity) {
        // write your code here
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    // @return an integer
    public int get(int key) {
        // write your code here
        if (!map.containsKey(key)) {
            return -1;
        }

        // 链表中在现在的位置移除
        Node node = map.get(key);
        node.prev.next = node.next;
        node.next.prev = node.prev;

        //插入到最后
        moveToTail(node);

        return node.value;
    }

    // @param key, an integer
    // @param value, an integer
    // @return nothing
    public void set(int key, int value) {
        // write your code here
        if (get(key) != -1) {
            map.get(key).value = value;
            return;
        }

        //判断是否达到缓存上限
        if (map.size() == capacity) {
            map.remove(head.next.key);
            head.next = head.next.next;
            head.next.prev = head;
        }

        //新节点放到最后
        Node node = new Node(key, value);
        map.put(key, node);
        moveToTail(node);
    }

    /**
     * 移动到最后
     *
     * @param node
     */
    private void moveToTail(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        node.prev.next = node;
        tail.prev = node;
    }
}
