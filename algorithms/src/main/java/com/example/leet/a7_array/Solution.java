package com.example.leet.a7_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {

  /**
   * 1. Two Sum
   * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
   * You may assume that each input would have exactly one solution, and you may not use the same element twice.
   * <p>
   * Example:
   * Given nums = [2, 7, 11, 15], target = 9,
   * Because nums[0] + nums[1] = 2 + 7 = 9,
   * return [0, 1].
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0, size = nums.length; i < size; i++) {
      if (map.containsKey(nums[i])) {
        return new int[] { map.get(nums[i]), i };
      } else {
        map.put(target - nums[i], i);
      }
    }
    return null;
  }

  /**
   * 4. Median of Two Sorted Arrays 中位数
   * There are two sorted arrays nums1 and nums2 of size m and n respectively.
   * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
   * Example 1:
   * nums1 = [1, 3]
   * nums2 = [2]
   * The median is 2.0
   * <p>
   * Example 2:
   * nums1 = [1, 2]
   * nums2 = [3, 4]
   * The median is (2 + 3)/2 = 2.5
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      int[] temp = nums2;
      nums2 = nums1;
      nums1 = temp;
    }
    int m = nums1.length;
    int n = nums2.length;

    int iMax = m, iMin = 0, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
      int i = (iMax + iMin) / 2;
      int j = halfLen - i;
      if (i < iMax && nums2[j - 1] > nums1[i]) {
        iMin = i + 1;
      } else if (i > iMin && nums1[i - 1] > nums2[j]) {
        iMax = i - 1;
      } else {
        int maxLeft = 0;
        if (i == 0) {
          maxLeft = nums2[j - 1];
        } else if (j == 0) {
          maxLeft = nums1[i - 1];
        } else {
          maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
        }
        if ((m + n) % 2 == 1) {
          return maxLeft;
        }

        int minRight = 0;
        if (i == m) {
          minRight = nums2[j];
        } else if (j == n) {
          minRight = nums1[i];
        } else {
          minRight = Math.min(nums1[i], nums2[j]);
        }
        return (minRight + maxLeft) / 2.0;
      }
    }
    return 0.0d;
  }

  /**
   * 11. Container With Most Water
   * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
   * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
   * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
   * Note: You may not slant the container and n is at least 2.
   *
   * @param height
   * @return
   */
  public int maxArea(int[] height) {
    int max = 0, left = 0, right = height.length - 1, area;
    while (left < right) {
      int l = height[left];
      int r = height[right];
      if (l > r) {
        area = r * (right - left);
        while (height[--right] <= r) {
        }
      } else {
        area = l * (right - left);
        while (height[++left] < l) {
        }
      }
      if (area > max) {
        max = area;
      }
    }
    return max;
  }

  /**
   * 15. 3Sum
   * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
   * Find all unique triplets in the array which gives the sum of zero.
   * Note: The solution set must not contain duplicate triplets.
   * <p>
   * For example, given array S = [-1, 0, 1, 2, -1, -4],
   * A solution set is:
   * [
   * [-1, 0, 1],
   * [-1, -1, 2]
   * ]
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> lists = new ArrayList<>();
    if (nums == null || nums.length < 3) {
      return lists;
    }
    Arrays.sort(nums);
    for (int i = 0, len = nums.length; i < len - 2; i++) {
      if ((i == 0 || (i > 0 && nums[i] != nums[i - 1])) && nums[i] <= 0) {
        int left = i + 1, right = nums.length - 1, remain = 0 - nums[i];
        while (left < right) {
          int sum = nums[left] + nums[right];
          if (sum == remain) {
            lists.add(Arrays.asList(nums[i], nums[left], nums[right]));
            while (left < right && nums[left] == nums[left + 1]) {
              left++;
            }
            while (left < right && nums[right] == nums[right - 1]) {
              right--;
            }
            left++;
            right--;
          } else if (sum < remain) {
            left++;
          } else {
            right--;
          }
        }
      }
    }
    return lists;
  }
}
