package com.example.book.a_1_3;

import java.util.Iterator;

/**
 * Created by Jiang on 2016/11/3.
 */

public class NodeStack<T> implements BaseStack<T>, Iterable<T> {

  private Node<T> head;
  private int N;

  @Override public boolean isEmpty() {
    return head == null;
  }

  @Override public int size() {
    return N;
  }

  @Override public void push(T t) {
    Node<T> oldFirst = head;
    head = new Node<T>();
    head.item = t;
    head.next = oldFirst;
    N++;
  }

  @Override public T pop() {
    Node<T> oldFirst = head;
    head = head.next;
    N--;
    return oldFirst == null ? null : oldFirst.item;
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
