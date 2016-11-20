package com.example.book.a_1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Jiang on 2016/11/7.
 */

public class TwoSumFast {

    public static final String PATH = "F:\\algs4-data\\1Kints.txt";

    public static void main(String[] args) {
        int[] arr = new In(PATH).readAllInts();
        StdOut.println(count(arr));
    }

    private static int count(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0, N = arr.length; i < N - 1; i++) {
            if (BinarySearch.indexOf(arr, -arr[i]) > i) {
                count++;
            }
        }
        return count;
    }
}
