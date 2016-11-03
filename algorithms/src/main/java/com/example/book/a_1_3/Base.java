package com.example.book.a_1_3;

/**
 * Created by Jiang on 2016/11/2.
 */

public interface Base<T> {

    /**
     * 是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 大小
     * @return
     */
    int size();

    /**
     * 添加元素
     * @param t
     */
    void push(T t);

    /**
     * 弹出元素
     * @return
     */
    T pop();
}
