package com.example.book.a_1_3;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by Jiang on 2016/11/2.
 */
public class Tests {

  public static void main(String[] aaa) {
    aaa3();
  }

  public static void aaa1() {
    ResizingArrayStack<Integer> stack = new ResizingArrayStack<>();
    while (!StdIn.isEmpty()) {
      stack.push(StdIn.readInt());
    }
    System.out.println("in end!");
    for (Integer i : stack) {
      System.out.println(i);
    }
  }

  public static void aaa2() {
    NodeStack<Integer> stack = new NodeStack<>();
    while (!StdIn.isEmpty()) {
      stack.push(StdIn.readInt());
    }
    System.out.println("in end!");
    for (Integer i : stack) {
      System.out.println(i);
    }
  }

  public static void aaa3() {
    NodeQueue<Integer> stack = new NodeQueue<>();
    while (!StdIn.isEmpty()) {
      stack.enqueue(StdIn.readInt());
    }
    System.out.println("in end!");
    for (Integer i : stack) {
      System.out.println(i);
    }
  }
}
