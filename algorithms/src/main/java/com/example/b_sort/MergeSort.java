package com.example.b_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by AckywOw on 2016/7/29.
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[10];
        int[] temps = new int[nums.length];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(10000) + 1;
        }

        System.out.println(Arrays.toString(nums));
        mergeSort(nums, temps);
        System.out.println(Arrays.toString(nums));
    }

    private static void mergeSort(int[] arr, int[] temps) {
        mergeSort(arr, 0, arr.length - 1, temps);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] temps) {
        if (left >= right)
            return;
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, temps);
        mergeSort(arr, mid + 1, right, temps);
        merge(arr, left, mid, right, temps);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temps) {
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            temps[k] = arr[k];
        }

        for (int k = left; k <= right; k++) {
            if      (i > mid)            arr[k] = temps[j++];
            else if (j > right)          arr[k] = temps[i++];
            else if (temps[i]>temps[j])  arr[k] = temps[j++];
            else                         arr[k] = temps[i++];
        }
    }
}
