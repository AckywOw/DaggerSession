package com.example.book.a_1_3;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by Jiang on 2016/11/2.
 */

public class Test {
    public static void main(String[] args) {
        ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
        while (!StdIn.isEmpty()) {
            stack.push(StdIn.readInt());
        }
        System.out.println("in end!");
        for(Integer i : stack) {
            System.out.println(i);
        }
    }
}
