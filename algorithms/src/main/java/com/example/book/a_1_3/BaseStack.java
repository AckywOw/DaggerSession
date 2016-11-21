package com.example.book.a_1_3;

/**
 * Created by Jiang on 2016/11/3.
 */

public interface BaseStack<T> extends Base<T> {

  /**
   * 添加元素
   */
  void push(T t);

  /**
   * 弹出元素
   */
  T pop();
}
