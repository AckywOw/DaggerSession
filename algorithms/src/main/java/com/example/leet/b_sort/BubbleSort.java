package com.example.leet.b_sort;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序
 * Created by AckywOw on 2016/6/29.
 */
public class BubbleSort {

  public static void main(String[] args) {
    int[] nums = new int[10];
    Random random = new SecureRandom();
    for (int i = 0; i < nums.length; i++) {
      nums[i] = random.nextInt(10000) + 1;
    }

    System.out.println(Arrays.toString(nums));
    bubbleSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  private static void bubbleSort(int[] nums) {
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = 0; j < nums.length - i - 1; j++) {
        if (nums[j] > nums[j + 1]) {
          int temp = nums[j];
          nums[j] = nums[j + 1];
          nums[j + 1] = temp;
        }
      }
    }
  }
}
