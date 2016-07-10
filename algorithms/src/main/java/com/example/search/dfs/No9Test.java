package com.example.search.dfs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * *** + *** = ***(1-9)
 * Created by AckywOw on 2016/6/12.
 */
public class No9Test {

    public static void main(String[] args) {
        int[] arr = new int[9];
        int[] book = new int[9];
        AtomicInteger sum = new AtomicInteger(0);
        no9(0, arr, book, sum);
        System.out.println("sum:" + sum.toString());
    }

    private static void no9(int step, int[] arr, int[] book, AtomicInteger sum) {
        if (step == arr.length && determine(arr)) {
            System.out.println("" + arr[0] + arr[1] + arr[2] + "+" + arr[3] + arr[4] + arr[5] +
                    "=" + arr[6] + arr[7] + arr[8]);
            sum.incrementAndGet();
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (book[i] == 0) {
                book[i] = 1;
                arr[step] = i + 1;
                no9(step + 1, arr, book, sum);
                book[i] = 0;
            }
        }
    }

    private static boolean determine(int[] arr) {
        return ((arr[0] * 100 + arr[1] * 10 + arr[2]) + (arr[3] * 100 + arr[4] * 10 + arr[5])) ==
                (arr[6] * 100 +
                        arr[7] * 10 + arr[8]);
    }


}
