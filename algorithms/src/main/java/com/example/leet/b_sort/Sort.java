package com.example.leet.b_sort;

/**
 * Created by Jiang on 2016/12/1.
 */

public class Sort {

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }

  public static void bubbleSort(int[] nums) {
    int N = nums.length;
    for (int i = 0; i < N - 1; i++) {
      for (int j = 0; j < N - i - 1; j++) {
        if (nums[j] > nums[j + 1]) {
          swap(nums, j, j + 1);
        }
      }
    }
  }

  public static void selectSort(int[] nums) {
    int N = nums.length;
    for (int i = 0; i < N - 1; i++) {
      int min = i;
      for (int j = i + 1; j < N; j++) {
        if (nums[min] > nums[j]) {
          min = j;
        }
      }
      swap(nums, i, min);
    }
  }

  public static void insertSort(int[] nums) {
    int N = nums.length;
    for (int i = 1; i < N; i++) {
      for (int j = i; j > 0; j--) {
        if (nums[j] < nums[j - 1]) {
          swap(nums, j, j - 1);
        }
      }
    }
  }

  public static void mergeSort(int[] nums) {
    int[] temps = new int[nums.length];
    mergeSortWrap(nums, 0, nums.length - 1, temps);
  }

  private static void mergeSortWrap(int[] nums, int left, int right, int[] temps) {
    if (left >= right) return;
    int mid = (left + right) / 2;
    mergeSortWrap(nums, left, mid, temps);
    mergeSortWrap(nums, mid + 1, right, temps);
    merge(nums, left, mid, right, temps);
  }

  private static void merge(int[] nums, int left, int mid, int right, int[] temps) {
    int i = left, j = mid + 1;
    for (int k = left; k <= right; k++) {
      temps[k] = nums[k];
    }

    for (int k = left; k <= right; k++) {
      if (i > mid) {
        nums[k] = temps[j++];
      } else if (j > right) {
        nums[k] = temps[i++];
      } else if (temps[i] > temps[j]) {
        nums[k] = temps[j++];
      } else {
        nums[k] = temps[i++];
      }
    }
  }

  public static void quickSort(int[] nums) {
    quickSort(nums, 0, nums.length - 1);
  }

  private static void quickSort(int[] nums, int left, int right) {
    if (left >= right) return;
    int i = left;
    int j = right;
    int temp = nums[left];

    while (i != j) {
      while (nums[j] >= temp && i < j) {
        j--;
      }
      while (nums[i] <= temp && i < j) {
        i++;
      }
      if (i < j) {
        swap(nums, i, j);
      }
    }

    nums[left] = nums[i];
    nums[i] = temp;

    quickSort(nums, left, i - 1);
    quickSort(nums, i + 1, right);
  }
}
