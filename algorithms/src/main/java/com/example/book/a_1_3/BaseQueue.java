package com.example.book.a_1_3;

/**
 * Created by Jiang on 2016/11/3.
 */

public interface BaseQueue<T> extends Base<T> {

    /**
     * 添加元素
     * @param t
     */
    void enqueue(T t);

    /**
     * 取出元素
     * @return
     */
    T dequeue();
}
