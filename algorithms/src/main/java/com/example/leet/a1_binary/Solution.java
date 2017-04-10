package com.example.leet.a1_binary;

/**
 * Created by AckywOw on 2016/8/1.
 */
public class Solution {
  /**
   * 二分查找
   * 给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
   *
   * @param nums: The integer array.
   * @param target: Target to find.
   * @return: The first position of target. Position starts from 0.
   */
  public int binarySearch(int[] nums, int target) {
    //write your code here
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int l = 0, r = nums.length - 1; // 在[l...r]的范围里寻找target
    while (l <= r) { // 当 l == r时,区间[l...r]依然是有效的
      int mid = l + (r - l) / 2; //这里是为了防止left + right会整型溢出
      if (nums[mid] == target) {
        r = mid;
      } else if (nums[mid] < target) {
        l = mid + 1;
      } else {
        r = mid - 1;
      }
    }
    if (nums[l] == target) {
      return l;
    }
    return -1;
  }

  /**
   * 二分查找
   * 给定一个排序的整数数组（升序）和一个要查找的整数target，用O(logn)的时间查找到target第一次出现的下标（从0开始），如果target不存在于数组中，返回-1。
   *
   * @param nums: The integer array.
   * @param target: Target to find.
   * @return: The first position of target. Position starts from 0.
   */
  public int binarySearch2(int[] nums, int target) {
    //write your code here
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int l = 0, r = nums.length; // 在[l...r)的范围里寻找target
    while (l < r) { //  当 l == r时,区间[l...r)是一个无小区间
      int mid = l + (r - l) / 2; //这里是为了防止left + right会整型溢出
      if (nums[mid] == target) {
        r = mid;
      } else if (nums[mid] < target) {
        l = mid + 1; // target在[mid+1...r)中; [l...mid]一定没有target
      } else {
        r = mid; // target在[l...mid)中; [mid...r)一定没有target
      }
    }
    if (nums[l] == target) {
      return l;
    }
    return -1;
  }
}
