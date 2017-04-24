package com.example.util;

import java.util.ArrayList;
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

  public static void printArrs(ArrayList<ArrayList<Integer>> lists) {
    for (ArrayList<Integer> list : lists) {
      System.out.print(list.toString());
    }
    System.out.println();
  }

  public static void printArrs(boolean[][] arr) {
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

  public static int min(int i, int r) {
    if (i > r) {
      return r;
    } else {
      return i;
    }
  }
}
