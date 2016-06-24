package com.example.tree;

import java.util.Arrays;

/**
 * 并查集
 * Created by AckywOw on 2016/6/22.
 */
public class DisjointSet {

    public static void main(String[] args) {
        int[] nums = new int[11];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = i;
        }

        int[][] map = {
                {1, 2},
                {3, 4},
                {5, 2},
                {4, 6},
                {2, 6},
                {8, 7},
                {9, 7},
                {1, 6},
                {2, 4}
        };

        for (int i = 0; i < map.length; i++) {
            merge(nums, map[i][0], map[i][1]);
        }

        System.out.println(Arrays.toString(nums));

    }

    private static void merge(int[] nums, int u, int v) {
        int t1 = getFather(nums, u);
        int t2 = getFather(nums, v);

        if (t1 != t2) { //说明是新的需要合并的
            nums[t2] = t1;
            getFather(nums, v); //设置未从父的子
        }
    }

    private static int getFather(int[] nums, int v) {
        if (nums[v] != v) {
            nums[v] = getFather(nums, nums[v]);
        }
        return nums[v];
    }
}
