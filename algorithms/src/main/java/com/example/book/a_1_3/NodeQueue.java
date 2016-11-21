package com.example.book.a_1_3;

import java.util.Iterator;

/**
 * Created by Jiang on 2016/11/3.
 */

public class NodeQueue<T> implements BaseQueue<T>, Iterable<T> {

  private Node<T> head;
  private Node<T> tail;
  private int N;

  @Override public boolean isEmpty() {
    return head == null;
  }

  @Override public int size() {
    return N;
  }

  @Override public void enqueue(T t) {
    Node<T> oldTail = tail;
    tail = new Node<T>();
    tail.item = t;
    if (isEmpty()) {
      head = tail;
    } else {
      oldTail.next = tail;
    }
    N++;
  }

  @Override public T dequeue() {
    if (!isEmpty()) {
      Node<T> oldFirst = head;
      head = head.next;
      N--;
      return oldFirst == null ? null : oldFirst.item;
    }
    return null;
  }

  @Override public Iterator<T> iterator() {
    return new PositiveIterator();
  }

  class PositiveIterator<T> implements Iterator<T> {

    Node<T> temp = (Node<T>) head;

    @Override public boolean hasNext() {
      return temp != null;
    }

    @Override public T next() {
      Node<T> next = temp;
      temp = temp.next;
      return next.item;
    }
  }
}
