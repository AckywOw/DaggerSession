package com.example.stack;

import java.util.Stack;

/**
 * 用栈实现队列
 * 正如标题所述，你需要使用两个栈来实现队列的一些操作。
 * 队列应支持push(element)，pop() 和 top()，其中pop是弹出队列中的第一个(最前面的)元素。
 * pop和top方法都应该返回第一个元素的值。
 * Created by AckywOw on 2016/7/29.
 */
public class Queue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public Queue() {
        // do initialization if necessary
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    public int pop() {
        // write your code here
        int temp;
        if (!stack2.isEmpty()) {
            temp = stack2.pop();
        } else {
            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }
            temp = stack1.pop();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return temp;
    }

    public int top() {
        // write your code 
        int temp;
        if (!stack2.isEmpty()) {
            temp = stack2.peek();
        } else {
            while (stack1.size() > 1) {
                stack2.push(stack1.pop());
            }
            temp = stack1.peek();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
        }
        return temp;
    }
}
