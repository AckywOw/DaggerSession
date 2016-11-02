package com.example.book.a_1_3;

/**
 * Created by Jiang on 2016/11/2.
 */

public interface Base<T> {

    boolean isEmpty();

    int size();

    void push(T t);

    T pop();

    void resize(int capacity);
}
