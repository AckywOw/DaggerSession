package com.example.book.a_1_4;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Jiang on 2016/11/7.
 */

public class ThreeSumFast {

    public static final String PATH = "F:\\algs4-data\\8Kints.txt";

    public static void main(String[] args) {
        int[] arr = new In(PATH).readAllInts();
        StdOut.println(count(arr));
    }

    private static int count(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        int N = arr.length;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                if (BinarySearch.indexOf(arr, -(arr[i] + arr[j])) > j) {
                    count++;
                }
            }
        }
        return count;
    }
}
