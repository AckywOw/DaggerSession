package com.example.book.a_1_3;

import java.util.Stack;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Jiang on 2016/11/2.
 */

public class BagExp {

    public static void main(String[] args) {
        In in = new In();
        Queue<Integer> queue = new Queue<>();
        while (!in.isEmpty()) {
            queue.enqueue(in.readInt());
        }

        System.out.println("in end!");

        for (int i : queue) {
            System.out.println(i);
        }
    }
}
