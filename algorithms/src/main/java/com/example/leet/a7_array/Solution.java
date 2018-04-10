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

  public int[] twoSum2(int[] nums, int target) {
    for (int i = 0; i < nums.length - 1; i++) {
      for (int j = 0; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[] { i, j };
        }
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

  /**
   * 16. 3Sum Closest
   * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
   * Return the sum of the three integers. You may assume that each input would have exactly one solution.
   * <p>
   * For example, given array S = {-1 2 1 -4}, and target = 1.
   * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
   *
   * @param nums
   * @param target
   * @return
   */
  public int threeSumClosest(int[] nums, int target) {
    int min = Integer.MAX_VALUE, sum = 0;
    if (nums.length <= 3) {
      for (int i = 0; i < nums.length; i++) {
        sum += nums[i];
      }
      return sum;
    }
    Arrays.sort(nums);
    for (int i = 0, len = nums.length; i < len - 2; i++) {
      int left = i + 1, right = nums.length - 1;
      while (left < right) {
        int plus = nums[i] + nums[left] + nums[right];
        int sub = plus - target;
        int m = Math.abs(sub);
        if (m < min) {
          min = m;
          sum = plus;
        }
        if (sub < 0) {
          do {
            left++;
          } while (left < right && nums[left] == nums[left - 1]);
        } else if (sub > 0) {
          do {
            right--;
          }
          while (left < right && nums[right] == nums[right + 1]);
        } else {
          return target;
        }
      }
    }
    return sum;
  }

  /**
   * 18. 4Sum
   * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target?
   * Find all unique quadruplets in the array which gives the sum of target.
   * Note: The solution set must not contain duplicate quadruplets.
   * <p>
   * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
   * A solution set is:
   * [
   * [-1,  0, 0, 1],
   * [-2, -1, 1, 2],
   * [-2,  0, 0, 2]
   * ]
   *
   * @param nums
   * @param target
   * @return
   */
  public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> lists = new ArrayList<>();
    if (nums.length < 4) {
      return lists;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
        break;
      }
      if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3]
          < target) {
        continue;
      }
      if (i > 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1; j < nums.length - 2; j++) {
        if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
          break;
        }
        if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
          continue;
        }
        if (j > i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        int left = j + 1, right = nums.length - 1;
        while (left < right) {
          int sum = nums[i] + nums[j] + nums[left] + nums[right];
          if (sum == target) {
            lists.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
            while (left < right && nums[left] == nums[left + 1]) {
              left++;
            }
            left++;
            while (left < right && nums[right] == nums[right - 1]) {
              right--;
            }
            right--;
          } else if (sum < target) {
            while (left < right && nums[left] == nums[left + 1]) {
              left++;
            }
            left++;
          } else {
            while (left < right && nums[right] == nums[right - 1]) {
              right--;
            }
            right--;
          }
        }
      }
    }
    return lists;
  }

  /**
   * 26. Remove Duplicates from Sorted Array
   * Given a sorted array, remove the duplicates in-place such that each element appear only once and return the new length.
   * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
   * <p>
   * Example:
   * Given nums = [1,1,2],
   * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
   * It doesn't matter what you leave beyond the new length.
   *
   * @param nums
   * @return
   */
  public int removeDuplicates(int[] nums) {
    int d = 0;
    for (int i = 0; i < nums.length; i++) {
      if (i == 0) {
        d = 1;
      } else {
        if (nums[i] == nums[i - 1]) {
          continue;
        }
        nums[d] = nums[i];
        d++;
      }
    }
    return d;
  }

  /**
   * 27. Remove Element
   * Given an array and a value, remove all instances of that value in-place and return the new length.
   * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
   * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
   * <p>
   * Example:
   * Given nums = [3,2,2,3], val = 3,
   * Your function should return length = 2, with the first two elements of nums being 2.
   *
   * @param nums
   * @param val
   * @return
   */
  public int removeElement(int[] nums, int val) {
    int d = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[d++] = nums[i];
      }
    }
    return d;
  }

  /**
   * 31. Next Permutation
   * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
   * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
   * The replacement must be in-place, do not allocate extra memory.
   * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
   * 1,2,3 → 1,3,2
   * 3,2,1 → 1,2,3
   * 1,1,5 → 1,5,1
   *
   * @param nums
   */
  public void nextPermutation(int[] nums) {
    int less = 0, index = 0, more = 0;
    for (int i = nums.length - 1; i > 0; i--) {
      if (nums[i] > nums[i - 1]) {
        less = i - 1;
        more = i;
        for (index = nums.length - 1; index > less; index--) {
          if (nums[index] > nums[less]) {
            break;
          }
        }
        break;
      }
    }
    swap(nums, less, index);
    reverse(nums, more);
  }

  private void swap(int[] nums, int left, int right) {
    int temp = nums[left];
    nums[left] = nums[right];
    nums[right] = temp;
  }

  private void reverse(int[] nums, int begin) {
    int end = nums.length - 1;
    while (begin < end) {
      swap(nums, begin, end);
      begin++;
      end--;
    }
  }
}
