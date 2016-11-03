package com.example.book.a_1_3;

import java.util.Iterator;

/**
 * Created by Jiang on 2016/11/3.
 */

public class NodeStack<T> implements Base<T>, Iterable<T> {

    private Node<T> head = new Node<T>();
    private int N = 1;

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public int size() {
        return N - 1;
    }

    @Override
    public void push(T t) {
        Node<T> oldFirst = head.next;
        head.next = new Node<T>();
        head.next.item = t;
        head.next.next = oldFirst;
        N++;
    }

    @Override
    public T pop() {
        if (N > 1) {
            Node<T> oldFirst = head.next;
            head.next = head.next.next;
            N--;
            return oldFirst == null ? null : oldFirst.item;
        }
        return null;
    }


    @Override
    public Iterator<T> iterator() {
        return new PositiveIterator();
    }

    class PositiveIterator<T> implements Iterator<T> {

        Node<T> temp = (Node<T>) head;

        @Override
        public boolean hasNext() {
            return temp != null && temp.next != null;
        }

        @Override
        public T next() {
            Node<T> next = temp.next;
            temp = temp.next;
            return next.item;
        }
    }
}
