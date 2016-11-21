package com.example.leet.a2_stack;

import java.util.Stack;

/**
 * 实现一个带有取最小值min方法的栈，min方法将返回当前栈中的最小值。
 * 你实现的栈将支持push，pop 和 min 操作，所有操作要求都在O(1)时间内完成。
 * <p/>
 * Created by AckywOw on 2016/7/29.
 */
public class MinStack {

  Stack<Integer> stack;
  int min;

  public MinStack() {
    // do initialize if necessary
    stack = new Stack<Integer>();
    min = 0;
  }

  public void push(int number) {
    // write your code here
    if (stack.isEmpty()) {
      stack.push(number);
      min = number;
    } else {
      if (number >= min) {
        stack.push(number);
      } else {
        stack.push(2 * number - min);
        min = number;
      }
    }
  }

  public int pop() {
    // write your code here
    if (stack.isEmpty()) {
      return min;
    } else {
      int temp = stack.pop();
      if (temp < min) {
        int num = min;
        min = 2 * min - temp;
        temp = num;
      }
      return temp;
    }
  }

  public int min() {
    // write your code here
    return min;
  }
}
