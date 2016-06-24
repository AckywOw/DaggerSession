package com.example.search.dfs;

import java.util.Arrays;

/**
 * Created by AckywOw on 2016/6/11.
 */
public class FullArray {

    public static void main(String[] args) {
        dfn(4);
    }

    private static void dfn(int num) {
        int arr[] = new int[num];
        int book[] = new int[num];
        dfs(0, num, arr, book);
    }

    private static void dfs(int step, int total, int[] arr, int[] book) {
        if (step >= total) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = 0; i < total; i++) {
            if (book[i] == 0) {
                arr[step] = i + 1;
                book[i] = 1;
                dfs(step + 1, total, arr, book);
                book[i] = 0;
            }
        }
    }
}
