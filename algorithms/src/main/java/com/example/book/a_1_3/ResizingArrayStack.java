package com.example.book.a_1_3;

import java.util.Iterator;

/**
 * Created by Jiang on 2016/11/2.
 */

public class ResizingArrayStack<T> implements BaseStack<T>, Resize, Iterable<T> {

  private T[] strs = (T[]) new Object[2];
  private int N;

  @Override public boolean isEmpty() {
    return N == 0;
  }

  @Override public int size() {
    return N;
  }

  @Override public void push(T t) {
    if (N == strs.length) {
      resize(strs.length * 2);
    }
    strs[N++] = t;
  }

  @Override public T pop() {
    T t = strs[--N];
    strs[N] = null;
    if (N > 0 && N == strs.length / 4) {
      resize(strs.length / 2);
    }
    return t;
  }

  @Override public void resize(int capacity) {
    T[] strs = (T[]) new Object[capacity];
    for (int i = 0; i < this.strs.length; i++) {
      strs[i] = this.strs[i];
    }
    this.strs = strs;
  }

  @Override public Iterator<T> iterator() {
    return new PositiveIterator();
  }

  /**
   * 正向迭代
   */
  private class PositiveIterator<T> implements Iterator<T> {
    private int i = 0;

    @Override public boolean hasNext() {
      return i < N;
    }

    @Override public T next() {
      return (T) strs[i++];
    }
  }

  /**
   * 反向迭代
   */
  private class ReverseIterator<T> implements Iterator<T> {
    private int i = N;

    @Override public boolean hasNext() {
      return i > 0;
    }

    @Override public T next() {
      return (T) strs[--i];
    }
  }
}
