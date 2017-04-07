package com.example.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by AckywOw on 2016/11/20.
 */

public class Utils {

  public static void printArrs(int[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      System.out.println(Arrays.toString(arr[i]));
    }
  }

  public static <T> void swap(List<T> list, int i, int k) {
    T t = list.get(i);
    list.set(i, list.get(k));
    list.set(k, t);
  }

  public static <T> void swap(T[] arr, int i, int k) {
    T t = arr[i];
    arr[i] = arr[k];
    arr[k] = t;
  }

  public static void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }
}
