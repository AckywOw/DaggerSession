package com.example.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[10];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(10000) +1;
        }

        System.out.println(Arrays.toString(nums));
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int i = left;
        int j = right;
        int temp = arr[left];
        int t;

        while (i != j) {
            while (arr[j] >= temp && i < j) {
                j--;
            }

            while (arr[i] <= temp && i < j) {
                i++;
            }

            if (i < j) {
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
                System.out.println(Arrays.toString(arr));
            }
        }

        arr[left] = arr[i];
        arr[i] = temp;
        System.out.println(Arrays.toString(arr));

        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
}
