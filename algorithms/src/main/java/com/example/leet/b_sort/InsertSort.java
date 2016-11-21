package com.example.leet.b_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 插入排序
 * Created by AckywOw on 2016/7/29.
 */
public class InsertSort {
  public static void main(String[] args) {
    int[] nums = new int[10];
    Random random = new Random();
    for (int i = 0; i < nums.length; i++) {
      nums[i] = random.nextInt(10000) + 1;
    }

    System.out.println(Arrays.toString(nums));
    insertSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  private static void insertSort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      for (int j = i; j > 0; j--) {
        if (nums[j] < nums[j - 1]) {
          int temp = nums[j];
          nums[j] = nums[j - 1];
          nums[j - 1] = temp;
        }
      }
    }
  }
}
