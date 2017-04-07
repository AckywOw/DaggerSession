package com.example.leet.b_sort;

import com.example.book.a_1_3.Node;
import com.example.util.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * Created by AckywOw on 2016/7/30.
 */
public class Solution {

  /**
   * 把数组中的0置后
   * @param arr
   */
  public static void moveZeros(Vector<Integer> arr) {
    //1
    //Vector<Integer> temp = new Vector<>();
    //for (int i = 0; i < arr.size(); i++) {
    //  if (arr.get(i) != 0) {
    //    temp.add(arr.get(i));
    //  }
    //}
    //for (int i = temp.size(); i < arr.size(); i++) {
    //  temp.add(0);
    //}

    //2
    //int k=0;
    //for (int i = 0; i < arr.size(); i++) {
    //  if(arr.get(i)!=0) {
    //    arr.set(k++,arr.get(i));
    //  }
    //}
    //while (k<arr.size()) {
    //  arr.set(k++,0);
    //}

    //3
    int k = 0;
    for (int i = 0; i < arr.size(); i++) {
      if (arr.get(i) != 0) {
        if (k != i) {
          Utils.swap(arr, k++, i);
        } else {
          k++;
        }
      }
    }
  }

  /**
   * 两个数组的交
   * 返回
   * nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2].
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public static int[] intersection(int[] nums1, int[] nums2) {
    if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
      return new int[] {};
    }
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int[] temp = new int[nums1.length];
    int i = 0;
    int j = 0;
    int index = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        if ((index == 0 || nums1[i] != temp[index - 1])) {
          temp[index++] = nums1[i];
        }
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {
        j++;
      }
    }
    int[] result = new int[index];
    for (int k = 0; k < index; k++) {
      result[k] = temp[k];
    }
    return result;
  }

  /**
   * 两个数组的交II
   * 返回
   * nums1 = [1, 2, 2, 1], nums2 = [2, 2], 返回 [2, 2].
   *
   * @param nums1
   * @param nums2
   * @return
   */
  public static int[] intersection2(int[] nums1, int[] nums2) {
    if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
      return new int[] {};
    }
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int[] temp = new int[nums1.length];
    int i = 0;
    int j = 0;
    int index = 0;
    while (i < nums1.length && j < nums2.length) {
      if (nums1[i] == nums2[j]) {
        temp[index++] = nums1[i];
        i++;
        j++;
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else {
        j++;
      }
    }
    int[] result = new int[index];
    for (int k = 0; k < index; k++) {
      result[k] = temp[k];
    }
    return result;
  }

  /**
   * 两数之和
   *
   * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。
   * 你需要实现的函数twoSum需要返回这两个数的下标, 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
   *
   * @param numbers : An array of Integer
   * @param target : target = numbers[index1] + numbers[index2]
   * @return : [index1 + 1, index2 + 1] (index1 < index2)
   */
  public static int[] twoSum(int[] numbers, int target) {
    // write your code here
    int[] result = new int[2];
    if (numbers == null && numbers.length <= 1) {
      return result;
    }
    HashMap<Integer, int[]> map = new HashMap<Integer, int[]>();
    for (int i = 0; i < numbers.length; i++) {
      int num = numbers[i];
      int[] values = map.get(num);
      if (values == null) {
        values = new int[] { i };
        map.put(target - num, values);
      } else {
        result[0] = values[0] + 1;
        result[1] = i + 1;
        break;
      }
    }
    return result;
  }

  /**
   * 两个数的和||
   * 给定一个排序数组，求出其中两个数的和等于指定target时，这两个数在原始数组中的下标,返回的下标从1开始
   *
   * @param numbers : An array of Integer
   * @param target : target = numbers[index1] + numbers[index2]
   * @return : [index1 + 1, index2 + 1] (index1 < index2)
   */
  public static int[] twoSum2(int[] numbers, int target) {
    // write your code here
    int[] result = new int[2];
    int left = 0, right = numbers.length - 1;
    while (left < right) {
      int sum = numbers[left] + numbers[right];
      if (sum < target) {
        left++;
      } else if (sum > target) {
        right--;
      } else {
        result[0] = left + 1;
        result[1] = right + 1;
        break;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    //        System.out.println(Arrays.toString(threeSum(new int[]{2, 7, 11, 15}));
  }

  /**
   * 链表插入排序
   *
   * 用插入排序对链表排序
   *
   * @param head
   * @return
   */
  public static Node<Integer> sortNode(Node<Integer> head) {
    Node<Integer> dummy = new Node<Integer>(0);
    // 这个dummy的作用是，把head开头的链表一个个的插入到dummy开头的链表里
    // 所以这里不需要dummy.next = head;

    while (head != null) {
      Node<Integer> node = dummy;
      while (node.next != null && node.next.item <= head.item) {
        node = node.next;
      }
      Node temp = head.next;
      head.next = node.next;
      node.next = head;
      head = temp;
    }
    return dummy.next;
  }

  /**
   * 最大数
   *
   * 给出一组非负整数，重新排列他们的顺序把他们组成一个最大的整数。(最后的结果可能很大，所以我们返回一个字符串来代替这个整数。)
   * 给出 [1, 20, 23, 4, 8]，返回组合最大的整数应为8423201。
   *
   * @param nums: A list of non negative integers
   * @return: A string
   */
  public static String largestNumber(int[] nums) {
    String[] strs = new String[nums.length];
    for (int i = 0; i < nums.length; i++) {
      strs[i] = Integer.toString(nums[i]);
    }
    Arrays.sort(strs, new NumbersComparator());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strs.length; i++) {
      sb.append(strs[i]);
    }
    String result = sb.toString();
    int index = 0;
    while (index < result.length() && result.charAt(index) == '0') {
      index++;
    }
    if (index == result.length()) {
      return "0";
    }
    return result.substring(index);
  }

  /**
   * 三数之和
   *
   * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
   * 在三元组(a, b, c)，要求a <= b <= c。
   * 结果不能包含重复的三元组。
   *
   * @param numbers : Give an array numbers of n integer
   * @return : Find all unique triplets in the array which gives the sum of zero.
   */
  public ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
    // write your code here
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    if (numbers == null || numbers.length < 3) {
      return result;
    }
    Arrays.sort(numbers);
    for (int i = 0; i < numbers.length; i++) {
      int left = i + 1, right = numbers.length - 1;
      while (left < right) {
        int sum = numbers[i] + numbers[left] + numbers[right];
        if (sum > 0) {
          right--;
        } else if (sum < 0) {
          left++;
        } else {
          ArrayList<Integer> path = new ArrayList<Integer>();
          path.add(numbers[i]);
          path.add(numbers[left]);
          path.add(numbers[right]);
          if (!result.contains(path)) result.add(path);
          right--;
          left++;
        }
      }
    }
    return result;
  }

  /**
   * 合并区间
   * 给出若干闭合区间，合并所有重叠的部分。
   *
   * 给出的区间列表 => 合并后的区间列表：
   * [                     [
   * [1, 3],               [1, 6],
   * [2, 6],      =>       [8, 10],
   * [8, 10],              [15, 18]
   * [15, 18]            ]
   * ]
   *
   * @param intervals, a collection of intervals
   * @return: A new sorted interval list.
   */
  public List<Interval> merge(List<Interval> intervals) {
    if (intervals == null || intervals.size() <= 1) {
      return intervals;
    }

    Collections.sort(intervals, new IntervalComparator());

    ArrayList<Interval> result = new ArrayList<Interval>();
    Interval last = intervals.get(0);
    for (int i = 1; i < intervals.size(); i++) {
      Interval curt = intervals.get(i);
      if (curt.start <= last.end) {
        last.end = Math.max(last.end, curt.end);
      } else {
        result.add(last);
        last = curt;
      }
    }

    result.add(last);
    return result;
  }

  /**
   * 最接近的三数之和
   * 给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。
   *
   * 例如 S = [-1, 2, 1, -4] and target = 1. 和最接近 1 的三元组是 -1 + 2 + 1 = 2.
   *
   * @param numbers: Give an array numbers of n integer
   * @param target : An integer
   * @return : return the sum of the three integers, the sum closest target.
   */
  public int threeSumClosest(int[] numbers, int target) {
    if (numbers == null || numbers.length < 3) {
      return -1;
    }

    Arrays.sort(numbers);
    int bestSum = numbers[0] + numbers[1] + numbers[2];
    for (int i = 0; i < numbers.length; i++) {
      int start = i + 1, end = numbers.length - 1;
      while (start < end) {
        int sum = numbers[i] + numbers[start] + numbers[end];
        if (Math.abs(target - sum) < Math.abs(target - bestSum)) {
          bestSum = sum;
        }
        if (sum < target) {
          start++;
        } else {
          end--;
        }
      }
    }

    return bestSum;
  }

  /**
   * 颜色分类
   * 给定一个包含红，白，蓝且长度为 n 的数组，将数组元素进行分类使相同颜色的元素相邻，并按照红、白、蓝的顺序进行排序。
   * 我们可以使用整数 0，1 和 2 分别代表红，白，蓝。
   *
   * 给你数组 [1, 0, 1, 2], 需要将该数组原地排序为 [0, 1, 1, 2]。
   *
   * @param nums: A list of integer which is 0, 1 or 2
   * @return: nothing
   */
  public void sortColors(int[] nums) {
    if (nums == null || nums.length <= 1) {
      return;
    }

    int pl = 0;
    int pr = nums.length - 1;
    int i = 0;
    while (i <= pr) {
      if (nums[i] == 0) {
        Utils.swap(nums, pl, i);
        pl++;
        i++;
      } else if (nums[i] == 1) {
        i++;
      } else {
        Utils.swap(nums, pr, i);
        pr--;
      }
    }
  }


  /**
   * 四数之和
   * 给一个包含n个数的整数数组S，在S中找到所有使得和为给定整数target的四元组(a, b, c, d)。
   * 注意事项
   * 四元组(a, b, c, d)中，需要满足a <= b <= c <= d
   * 答案中不可以包含重复的四元组。
   *
   * 例如，对于给定的整数数组S=[1, 0, -1, 0, -2, 2] 和 target=0. 满足要求的四元组集合为：
   * (-1, 0, 0, 1)
   * (-2, -1, 1, 2)
   * (-2, 0, 0, 2)
   *
   * @param numbers : Give an array numbersbers of n integer
   * @param target : you need to find four elements that's sum of target
   * @return : Find all unique quadruplets in the array which gives the sum of
   * zero.
   */
  public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
    ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
    Arrays.sort(numbers);

    for (int i = 0; i < numbers.length - 3; i++) {
      if (i != 0 && numbers[i] == numbers[i - 1]) {
        continue;
      }

      for (int j = i + 1; j < numbers.length - 2; j++) {
        if (j != i + 1 && numbers[j] == numbers[j - 1]) continue;

        int left = j + 1;
        int right = numbers.length - 1;
        while (left < right) {
          int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
          if (sum < target) {
            left++;
          } else if (sum > target) {
            right--;
          } else {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(numbers[i]);
            tmp.add(numbers[j]);
            tmp.add(numbers[left]);
            tmp.add(numbers[right]);
            rst.add(tmp);
            left++;
            right--;
            while (left < right && numbers[left] == numbers[left - 1]) {
              left++;
            }
            while (left < right && numbers[right] == numbers[right + 1]) {
              right--;
            }
          }
        }
      }
    }

    return rst;
  }

  /**
   * 最大间距
   * 给定一个未经排序的数组，请找出其排序表中连续两个要素的最大间距。
   * 如果数组中的要素少于 2 个，请返回 0.
   * 可以假定数组中的所有要素都是非负整数，且最大不超过 32 位整数。
   *
   * 给定数组 [1, 9, 2, 5]，其排序表为 [1, 2, 5, 9]，其最大的间距是在 5 和 9 之间，= 4.
   *
   * @param nums: an array of integers
   * @return: the maximum difference
   */
  public int maximumGap(int[] nums) {
    if (nums.length < 2) return 0;
    int minNum = -1, maxNum = -1, n = nums.length;
    for (int i = 0; i < n; ++i) {
      minNum = min(nums[i], minNum);
      maxNum = max(nums[i], maxNum);
    }
    if (maxNum == minNum) return 0;
    int average = (maxNum - minNum) / (n - 1);
    if (average == 0) ++average;
    int[] localMin = new int[n];
    int[] localMax = new int[n];
    for (int i = 0; i < n; ++i) {
      localMin[i] = -1;
      localMax[i] = -1;
    }
    for (int i = 0; i < n; ++i) {
      int t = (nums[i] - minNum) / average;
      localMin[t] = min(localMin[t], nums[i]);
      localMax[t] = max(localMax[t], nums[i]);
    }
    int ans = average, left = 0, right = 1;
    while (left < n - 1) {
      while (right < n && localMin[right] == -1) ++right;
      if (right >= n) break;
      ans = max(ans, localMin[right] - localMax[left]);
      left = right;
      ++right;
    }
    return ans;
  }

  private int min(int a, int b) {
    if (a == -1) {
      return b;
    } else if (b == -1) {
      return a;
    } else if (a < b) {
      return a;
    } else {
      return b;
    }
  }

  private int max(int a, int b) {
    if (a == -1) {
      return b;
    } else if (b == -1) {
      return a;
    } else if (a > b) {
      return a;
    } else {
      return b;
    }
  }

  /**
   * 摆动排序
   * 给你一个没有排序的数组，请将原数组就地重新排列满足如下性质
   * nums[0] <= nums[1] >= nums[2] <= nums[3]....
   * 请就地排序数组，也就是不需要额外数组
   *
   * 给出数组为 nums = [3, 5, 2, 1, 6, 4] 一种输出方案为 [1, 6, 2, 5, 3, 4]
   *
   * @param nums a list of integer
   * @return void
   */
  public void wiggleSort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if ((i % 2 == 1 && (nums[i] < nums[i - 1]) || (i % 2 == 0) && (nums[i] > nums[i - 1]))) {
        Utils.swap(nums, i - 1, i);
      }
    }
  }

  static class NumbersComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
      return (s2 + s1).compareTo(s1 + s2);
    }
  }

  private class IntervalComparator implements Comparator<Interval> {
    public int compare(Interval a, Interval b) {
      return a.start - b.start;
    }
  }

  class Interval {
    int start, end;

    Interval(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }
}
