package com.example.leet.a7_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution {

  public String a;

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

  /**
   * 33. Search in Rotated Sorted Array
   * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
   * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
   * You are given a target value to search. If found in the array return its index, otherwise return -1.
   * You may assume no duplicate exists in the array.
   * Your algorithm's runtime complexity must be in the order of O(log n).
   * <p>
   * Example 1:
   * Input: nums = [4,5,6,7,0,1,2], target = 0
   * Output: 4
   * Example 2:
   * Input: nums = [4,5,6,7,0,1,2], target = 3
   * Output: -1
   *
   * @param nums
   * @param target
   * @return
   */
  public int search(int[] nums, int target) {
    int left = 0, right = nums.length - 1;
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (nums[mid] > nums[right]) {
        if (target > nums[mid] || target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid;
        }
      } else {
        if (target > nums[mid] && target <= nums[right]) {
          left = mid + 1;
        } else {
          right = mid;
        }
      }
    }
    if (left == right && target != nums[left] || nums.length == 0) {
      return -1;
    }
    return left;
  }

  /**
   * 34. Search for a Range
   * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
   * Your algorithm's runtime complexity must be in the order of O(log n).
   * If the target is not found in the array, return [-1, -1].
   * <p>
   * Example 1:
   * Input: nums = [5,7,7,8,8,10], target = 8
   * Output: [3,4]
   * Example 2:
   * Input: nums = [5,7,7,8,8,10], target = 6
   * Output: [-1,-1]
   *
   * @param nums
   * @param target
   * @return
   */
  public int[] searchRange(int[] nums, int target) {
    int[] targetRange = { -1, -1 };
    int leftIdx = findIndex(nums, target, 0, true);

    // assert that `leftIdx` is within the array bounds and that `target`
    // is actually in `nums`.
    if (leftIdx == nums.length || nums[leftIdx] != target) {
      return targetRange;
    }

    targetRange[0] = leftIdx;
    targetRange[1] = findIndex(nums, target, leftIdx, false) - 1;

    return targetRange;
  }

  private int findIndex(int[] nums, int target, int start, boolean left) {
    int end = nums.length;
    while (start < end) {
      int mid = start + (end - start) / 2;
      if (nums[mid] > target || target == nums[mid] && left) {
        end = mid;
      } else {
        start = mid + 1;
      }
    }
    return start;
  }

  /**
   * 35. Search Insert Position
   * Given a sorted array and a target value, return the index if the target is found.
   * If not, return the index where it would be if it were inserted in order.
   * You may assume no duplicates in the array.
   * <p>
   * Example 1:
   * Input: [1,3,5,6], 5
   * Output: 2
   * Example 2:
   * Input: [1,3,5,6], 2
   * Output: 1
   * Example 3:
   * Input: [1,3,5,6], 7
   * Output: 4
   * Example 4:
   * Input: [1,3,5,6], 0
   * Output: 0
   *
   * @param nums
   * @param target
   * @return
   */
  public int searchInsert(int[] nums, int target) {
    int low = 0, high = nums.length;
    while (low < high) {
      int mid = low + (high - low) / 2; // low<=mid, mid<high
      if (nums[mid] >= target) {
        high = mid; // high always decreases (even high-low==1)
      } else {
        low = mid + 1; // low always increases
      }
    }
    return low;
  }

  /**
   * 39. Combination Sum
   * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
   * find all unique combinations in candidates where the candidate numbers sums to target.
   * The same repeated number may be chosen from candidates unlimited number of times.
   * Note:
   * All numbers (including target) will be positive integers.
   * The solution set must not contain duplicate combinations.
   * <p>
   * Example 1:
   * Input: candidates = [2,3,6,7], target = 7,
   * A solution set is:
   * [
   * [7],
   * [2,2,3]
   * ]
   * Example 2:
   * Input: candidates = [2,3,5], target = 8,
   * A solution set is:
   * [
   * [2,2,2,2],
   * [2,3,3],
   * [3,5]
   * ]
   *
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> lists = new ArrayList<>();
    combinationSum(candidates, target, 0, 0, new Integer[target], lists);
    return lists;
  }

  private void combinationSum(int[] candidates, int target, int len, int start, Integer[] paper,
      List<List<Integer>> lists) {
    if (target == 0) {
      Integer[] temp = new Integer[len];
      System.arraycopy(paper, 0, temp, 0, len);
      lists.add(Arrays.asList(temp));
    } else {
      for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
        paper[len] = candidates[i];
        combinationSum(candidates, target - candidates[i], len + 1, i, paper, lists);
      }
    }
  }

  /**
   * 40. Combination Sum II
   * Given a collection of candidate numbers (candidates) and a target number (target),
   * find all unique combinations in candidates where the candidate numbers sums to target.
   * Each number in candidates may only be used once in the combination.
   * Note:
   * All numbers (including target) will be positive integers.
   * The solution set must not contain duplicate combinations.
   * <p>
   * Example 1:
   * Input: candidates = [10,1,2,7,6,1,5], target = 8,
   * A solution set is:
   * [
   * [1, 7],
   * [1, 2, 5],
   * [2, 6],
   * [1, 1, 6]
   * ]
   * Example 2:
   * Input: candidates = [2,5,2,1,2], target = 5,
   * A solution set is:
   * [
   * [1,2,2],
   * [5]
   * ]
   *
   * @param candidates
   * @param target
   * @return
   */
  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> lists = new ArrayList<>();

    combinationSum2(candidates, target, 0, new Integer[candidates.length], 0, lists);
    return lists;
  }

  private void combinationSum2(int[] candidates, int target, int start, Integer[] paper, int len,
      List<List<Integer>> lists) {
    if (target == 0) {
      Integer[] temp = new Integer[len];
      System.arraycopy(paper, 0, temp, 0, len);
      lists.add(Arrays.asList(temp));
    } else {
      for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
        if (i > start && candidates[i] == paper[len]) {
          continue;
        }
        paper[len] = candidates[i];
        combinationSum2(candidates, target - candidates[i], i + 1, paper, len + 1, lists);
      }
    }
  }

  /**
   * 48. Rotate Image
   * You are given an n x n 2D matrix representing an image.
   * Rotate the image by 90 degrees (clockwise).
   * Note:
   * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
   * DO NOT allocate another 2D matrix and do the rotation.
   * <p>
   * Example 1:
   * Given input matrix =
   * [
   * [1,2,3],
   * [4,5,6],
   * [7,8,9]
   * ],
   * rotate the input matrix in-place such that it becomes:
   * [
   * [7,4,1],
   * [8,5,2],
   * [9,6,3]
   * ]
   * Example 2:
   * Given input matrix =
   * [
   * [ 5, 1, 9,11],
   * [ 2, 4, 8,10],
   * [13, 3, 6, 7],
   * [15,14,12,16]
   * ],
   * rotate the input matrix in-place such that it becomes:
   * [
   * [15,13, 2, 5],
   * [14, 3, 4, 1],
   * [12, 6, 8, 9],
   * [16, 7,10,11]
   * ]
   *
   * @param matrix
   */
  public void rotate(int[][] matrix) {
    int n = matrix.length;
    int halfOfN = n / 2;
    for (int i = 0; i < halfOfN; i++) {
      for (int j = i; j < n - i - 1; j++) {
        swapMatrix(matrix, new int[] { i, j }, new int[] { j, n - 1 - i },
            new int[] { n - 1 - i, n - 1 - j },
            new int[] { n - 1 - j, i });
      }
    }
  }

  private void swapMatrix(int[][] matrix, int[] index1, int[] index2, int[] index3, int[] index4) {
    int temp = matrix[index1[0]][index1[1]];
    matrix[index1[0]][index1[1]] = matrix[index4[0]][index4[1]];
    matrix[index4[0]][index4[1]] = matrix[index3[0]][index3[1]];
    matrix[index3[0]][index3[1]] = matrix[index2[0]][index2[1]];
    matrix[index2[0]][index2[1]] = temp;
  }

  /**
   * 53. Maximum Subarray
   * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
   * <p>
   * Example:
   * Input: [-2,1,-3,4,-1,2,1,-5,4],
   * Output: 6
   * Explanation: [4,-1,2,1] has the largest sum = 6.
   * Follow up:
   * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
   *
   * @param nums
   * @return
   */
  public int maxSubArray(int[] nums) {
    int max = nums[0], maxEndingHere = nums[0];
    for (int i = 1; i < nums.length; ++i) {
      maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
      max = Math.max(max, maxEndingHere);
    }
    return max;
  }

  /**
   * 54. Spiral Matrix
   * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
   * Example 1:
   * Input:
   * [
   * [ 1, 2, 3 ],
   * [ 4, 5, 6 ],
   * [ 7, 8, 9 ]
   * ]
   * Output: [1,2,3,6,9,8,7,4,5]
   * Example 2:
   * Input:
   * [
   * [1, 2, 3, 4],
   * [5, 6, 7, 8],
   * [9,10,11,12]
   * ]
   * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
   *
   * @param matrix
   * @return
   */
  public List<Integer> spiralOrder(int[][] matrix) {
    //List<Integer> list = new ArrayList<>();
    //if (matrix.length == 0 || matrix[0].length == 0) {
    //  return list;
    //}
    //boolean[][] map = new boolean[matrix.length][matrix[0].length];
    //int[] step = { 0, 0, 1 };
    //int index = 1, total = matrix.length * matrix[0].length;
    //while (index <= total) {
    //  map[step[0]][step[1]] = true;
    //  list.add(matrix[step[0]][step[1]]);
    //
    //  if (step[2] == 1) {//right
    //    if (step[1] == matrix[0].length - 1
    //        || map[step[0]][step[1] + 1]) {
    //      step[2] = 2;
    //      step[0] += 1;
    //    } else {
    //      step[1] += 1;
    //    }
    //  } else if (step[2] == 2) {//down
    //    if (step[0] == matrix.length - 1 || map[step[0]
    //        + 1][step[1]]) {
    //      step[2] = 3;
    //      step[1] -= 1;
    //    } else {
    //      step[0] += 1;
    //    }
    //  } else if (step[2] == 3) {//left
    //    if (step[1] == 0 || map[step[0]][step[1] - 1]) {
    //      step[2] = 4;
    //      step[0] -= 1;
    //    } else {
    //      step[1] -= 1;
    //    }
    //  } else if (step[2] == 4) {//up
    //    if (step[0] == 0 || map[step[0] - 1][step[1]]) {
    //      step[2] = 1;
    //      step[1] += 1;
    //    } else {
    //      step[0] -= 1;
    //    }
    //  }
    //  index++;
    //}
    //return list;

    List<Integer> list = new ArrayList<>();
    if (matrix.length == 0 || matrix[0].length == 0) {
      return list;
    }
    int curLeft = 0, curUp = 0, curRight = matrix[0].length - 1, curDown = matrix.length - 1;
    while (curLeft <= curRight && curUp <= curDown) {
      for (int i = curLeft; i <= curRight; i++) {
        list.add(matrix[curUp][i]);
      }
      curUp++;

      for (int i = curUp; i <= curDown; i++) {
        list.add(matrix[i][curRight]);
      }
      curRight--;

      if (curUp <= curDown) {
        for (int i = curRight; i >= curLeft; i--) {
          list.add(matrix[curDown][i]);
        }
      }
      curDown--;

      if (curLeft <= curRight) {
        for (int i = curDown; i >= curUp; i--) {
          list.add(matrix[i][curLeft]);
        }
      }
      curLeft++;
    }
    return list;
  }

  /**
   * 59. Spiral Matrix II
   * Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
   * <p>
   * Example:
   * Input: 3
   * Output:
   * [
   * [ 1, 2, 3 ],
   * [ 8, 9, 4 ],
   * [ 7, 6, 5 ]
   * ]
   *
   * @param n
   * @return
   */
  public int[][] generateMatrix(int n) {
    int[][] arr = new int[n][n];
    int curLeft = 0, curUp = 0, curRight = n - 1, curDown = n - 1, curNum = 1;
    while (curLeft <= curRight && curUp <= curDown) {
      for (int i = curLeft; i <= curRight; i++) {
        arr[curUp][i] = curNum++;
      }
      curUp++;

      for (int i = curUp; i <= curDown; i++) {
        arr[i][curRight] = curNum++;
      }
      curRight--;

      for (int i = curRight; i >= curLeft; i--) {
        arr[curDown][i] = curNum++;
      }
      curDown--;

      for (int i = curDown; i >= curUp; i--) {
        arr[i][curLeft] = curNum++;
      }
      curLeft++;
    }
    return arr;
  }

  /**
   * 66. Plus One
   * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
   * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
   * You may assume the integer does not contain any leading zero, except the number 0 itself.
   * <p>
   * Example 1:
   * Input: [1,2,3]
   * Output: [1,2,4]
   * Explanation: The array represents the integer 123.
   * Example 2:
   * Input: [4,3,2,1]
   * Output: [4,3,2,2]
   * Explanation: The array represents the integer 4321.
   *
   * @param digits
   * @return
   */
  public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >= 0; i--) {
      if (digits[i] < 9) {
        digits[i]++;
        break;
      } else {
        digits[i] = 0;
        if (i == 0) {
          int[] arr = new int[digits.length + 1];
          arr[0] = 1;
          System.arraycopy(digits, 0, arr, 1, digits.length);
          return arr;
        } else {
          continue;
        }
      }
    }
    return digits;
  }

  /**
   * 55. Jump Game
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   * Each element in the array represents your maximum jump length at that position.
   * Determine if you are able to reach the last index.
   * <p>
   * Example 1:
   * Input: [2,3,1,1,4]
   * Output: true
   * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
   * Example 2:
   * Input: [3,2,1,0,4]
   * Output: false
   * Explanation: You will always arrive at index 3 no matter what. Its maximum
   * jump length is 0, which makes it impossible to reach the last index.
   *
   * @param nums
   * @return
   */
  public boolean canJump(int[] nums) { //Greedy
    //int lastPos = nums.length - 1;
    //for (int i = nums.length - 1; i >= 0; i--) {
    //  if (i + nums[i] >= lastPos) {
    //    lastPos = i;
    //  }
    //}
    //return lastPos == 0;
    int last = 0;
    for (int i = 0; i <= last; i++) {
      last = Math.max(last, i + nums[i]);
      if (last >= nums.length - 1) {
        return true;
      }
    }
    return false;
  }

  /**
   * 45. Jump Game II
   * Given an array of non-negative integers, you are initially positioned at the first index of the array.
   * Each element in the array represents your maximum jump length at that position.
   * Your goal is to reach the last index in the minimum number of jumps.
   * <p>
   * Example:
   * Input: [2,3,1,1,4]
   * Output: 2
   * Explanation: The minimum number of jumps to reach the last index is 2.
   * Jump 1 step from index 0 to 1, then 3 steps to the last index.
   * Note:
   * You can assume that you can always reach the last index.
   *
   * @param nums
   * @return
   */
  public int jump(int[] nums) {
    int step = 0, curEnd = 0, curFurthest = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      curFurthest = Math.max(curFurthest, i + nums[i]);
      if (i == curEnd) {
        step++;
        curEnd = curFurthest;
      }
    }
    return step;
  }

  /**
   * 121. Best Time to Buy and Sell Stock
   * Say you have an array for which the ith element is the price of a given stock on day i.
   * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
   * Note that you cannot sell a stock before you buy one.
   * <p>
   * Example 1:
   * Input: [7,1,5,3,6,4]
   * Output: 5
   * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
   * Not 7-1 = 6, as selling price needs to be larger than buying price.
   * Example 2:
   * Input: [7,6,4,3,1]
   * Output: 0
   * Explanation: In this case, no transaction is done, i.e. max profit = 0.
   *
   * @param prices
   * @return
   */
  public int maxProfit(int[] prices) {
    if (prices.length == 0) {
      return 0;
    }
    int profit = 0, min = prices[0];
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < min) {
        min = prices[i];
      } else if (prices[i] - min > profit) {
        profit = prices[i] - min;
      }
    }
    return profit;
  }

  /**
   * 122. Best Time to Buy and Sell Stock II
   * Say you have an array for which the ith element is the price of a given stock on day i.
   * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
   * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
   * <p>
   * Example 1:
   * Input: [7,1,5,3,6,4]
   * Output: 7
   * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
   * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
   * Example 2:
   * Input: [1,2,3,4,5]
   * Output: 4
   * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
   * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
   * engaging multiple transactions at the same time. You must sell before buying again.
   * Example 3:
   * Input: [7,6,4,3,1]
   * Output: 0
   * Explanation: In this case, no transaction is done, i.e. max profit = 0.
   *
   * @param prices
   * @return
   */
  public int maxProfit2(int[] prices) {
    if (prices.length <= 1) {
      return 0;
    }
    int profit = 0;
    for (int i = 0; i < prices.length - 1; i++) {
      if (prices[i] < prices[i + 1]) {
        profit += prices[i + 1] - prices[i];
      }
    }
    return profit;
  }

  /**
   * 123. Best Time to Buy and Sell Stock III
   * Say you have an array for which the ith element is the price of a given stock on day i.
   * Design an algorithm to find the maximum profit. You may complete at most two transactions.
   * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
   * <p>
   * Example 1:
   * Input: [3,3,5,0,0,3,1,4]
   * Output: 6
   * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
   * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
   * Example 2:
   * Input: [1,2,3,4,5]
   * Output: 4
   * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
   * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
   * engaging multiple transactions at the same time. You must sell before buying again.
   * Example 3:
   * Input: [7,6,4,3,1]
   * Output: 0
   * Explanation: In this case, no transaction is done, i.e. max profit = 0.
   *
   * @param prices
   * @return
   */
  public int maxProfit3(int[] prices) {
    int buy1 = Integer.MIN_VALUE;   //profit after buy 1st stock
    int buy2 = Integer.MIN_VALUE;   //profit after buy 2nd stock
    int sell1 = 0;                  //profit after sell 1st stock
    int sell2 = 0;                  //profit after sell 2nd stock
    for (int p : prices) {
      sell2 = Math.max(sell2, buy2 + p);
      buy2 = Math.max(buy2, sell1 - p);
      sell1 = Math.max(sell1, buy1 + p);
      buy1 = Math.max(buy1, -p);
    }
    return sell2;
  }
}
