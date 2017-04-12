package com.example.leet.b_sort;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by AckywOw on 2016/7/29.
 */
public class MergeSort {
  public static void main(String[] args) {
    int[] nums = new int[10];
    int[] temps = new int[nums.length];
    Random random = new SecureRandom();
    for (int i = 0; i < nums.length; i++) {
      nums[i] = random.nextInt(10000) + 1;
    }

    System.out.println(Arrays.toString(nums));
    mergeSort3(nums, temps);
    System.out.println(Arrays.toString(nums));
  }

  /**
   * 自顶向下
   *
   * @param arr
   * @param temps
   */
  private static void mergeSort(int[] arr, int[] temps) {
    mergeSort(arr, 0, arr.length - 1, temps);
  }

  private static void mergeSort(int[] arr, int left, int right, int[] temps) {
    if (left >= right) return;
    int mid = (left + right) / 2;
    mergeSort(arr, left, mid, temps);
    mergeSort(arr, mid + 1, right, temps);
    merge(arr, left, mid, right, temps);
  }

  /**
   * 自底向上
   *
   * @param arr
   * @param temps
   */
  private static void mergeSort2(int[] arr, int[] temps) {
    int i = 1;
    while (i < arr.length) {
      for (int j = 0; j + i <= arr.length - 1; j += i * 2) {
        int right = j + i * 2 - 1;
        if (right > arr.length - 1) right = arr.length - 1;
        merge(arr, j, j + i - 1, right, temps);
      }
      i *= 2;
    }
  }

  /**
   * 自然归并
   *
   * @param arr
   * @param temps
   */
  private static void mergeSort3(int[] arr, int[] temps) {
    List<Integer> records = new ArrayList<>();
    scan(arr, records);
    if (records.size() <= 1) return;
    while (records.size() >= 2) {
      for (int i = 0; i < records.size(); i += 2) {
        int right;
        int mid;
        if (i + 1 == records.size()) {
          continue;
        } else if (i + 2 == records.size()) {
          right = arr.length - 1;
          mid = records.get(i + 1) - 1;
        } else {
          mid = records.get(i + 1) - 1;
          right = records.get(i + 2) - 1;
        }
        merge(arr, records.get(i), mid, right, temps);
        System.out.println("in-" + Arrays.toString(arr));
      }
      System.out.println("out-" + Arrays.toString(arr));
      scan(arr, records);
      //List<Integer> list = new ArrayList<>();
      //for (int i = 0; i < records.size(); i+=2) {
      //  list.add(records.get(i));
      //}
      //records = list;
    }
  }

  private static void scan(int[] arr, List<Integer> records) {
    records.clear();
    int bigger = arr[0];
    records.add(0);
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < bigger) {
        bigger = arr[i];
        records.add(i);
      } else {
        bigger = arr[i];
      }
    }
  }

  private static void merge(int[] arr, int left, int mid, int right, int[] temps) {
    int i = left, j = mid + 1;
    for (int k = left; k <= right; k++) {
      temps[k] = arr[k];
    }

    for (int k = left; k <= right; k++) {
      if (i > mid) {
        arr[k] = temps[j++];
      } else if (j > right) {
        arr[k] = temps[i++];
      } else if (temps[i] > temps[j]) {
        arr[k] = temps[j++];
      } else {
        arr[k] = temps[i++];
      }
    }
  }
}
