package com.example.leet.b_sort;

import com.example.book.a_1_3.Node;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

import static com.example.leet.b_sort.Solution.sortNode;
import static com.example.leet.b_sort.Sort.quickSort;

/**
 * Created by Jiang on 2016/12/1.
 */

public class Test_Sort {
  public static void main(String[] args) {
    //arraySort();
    linkedSort();
  }

  private static void arraySort() {
    int[] nums = new int[10];
    Random random = new Random();
    for (int i = 0; i < nums.length; i++) {
      nums[i] = random.nextInt(100) + 1;
    }

    System.out.println(Arrays.toString(nums));
    quickSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  private static void linkedSort() {
    Random random = new Random();
    Node<Integer> first = null;
    Node<Integer> now = null;
    for (int i = 0; i < 10; i++) {
      Node<Integer> temp = new Node<>();
      temp.item = random.nextInt(100) + 1;
      if (first == null) {
        first = temp;
        now = first;
      } else {
        now.next = temp;
        now = temp;
      }
    }

    System.out.println(first);
    first = sortNode(first);
    System.out.println(first);
  }

  public static int[] twoSum(int[] numbers, int target) {
    int result[] = new int[2];
    if (numbers == null || numbers.length < 2) {
      return result;
    }
    HashMap<Integer, Integer[]> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      int temp = numbers[i];
      Integer[] integer = map.get(temp);
      if (integer == null) {
        map.put(target - temp, new Integer[] { i });
      } else {
        result[0] = integer[0] + 1;
        result[1] = i + 1;
      }
    }
    return result;
  }

  public static ArrayList<ArrayList<Integer>> threeSum(int[] numbers) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
    if (numbers == null || numbers.length < 3) {
      return result;
    }

    Arrays.sort(numbers);
    for (int i = 0; i < numbers.length; i++) {
      int left = i + 1, right = numbers.length - 1;
      while (left < right) {
        int sum = numbers[i] + numbers[left] + numbers[right];
        if (sum < 0) {
          left++;
        } else if (sum > 0) {
          right--;
        } else {
          ArrayList<Integer> list = new ArrayList<>();
          list.add(numbers[i]);
          list.add(numbers[left]);
          list.add(numbers[right]);
          if (!result.contains(list)) result.add(list);
          left++;
          right--;
        }
      }
    }
    return result;
  }

  public static String largestNumber(int[] nums) {
    ArrayList<String> strs = new ArrayList<>();
    for (int i = 0; i < nums.length; i++) {
      strs.add(String.valueOf(nums[i]));
    }
    Collections.sort(strs, new NumbersComparator());
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < strs.size(); i++) {
      sb.append(strs.get(i));
    }
    String s = sb.toString();
    int index = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '0') {
        index++;
      }
    }
    if (index == s.length()) {
      return "0";
    }
    return s;
  }

  public int threeSumClosest(int[] numbers, int target) {
    if (numbers == null || numbers.length < 3) {
      return -1;
    }

    Arrays.sort(numbers);
    int bestSum = numbers[0] + numbers[1] + numbers[2];
    for (int i = 0; i < numbers.length - 2; i++) {
      int left = i + 1, right = numbers.length - 1;
      while (left < right) {
        int sum = numbers[i] + numbers[left] + numbers[right];
        if (Math.abs(sum - target) < Math.abs(bestSum - target)) {
          bestSum = sum;
        }
        if (sum < target) {
          left++;
        } else {
          right--;
        }
      }
    }
    return bestSum;
  }

  public void sortColors(int[] nums) {
    if (nums == null || nums.length == 0) return;
    Arrays.sort(nums);
    int i = 0, left = 0, right = nums.length - 1;
    while (i < right) {
      if (nums[i] == 0) {
        swap(nums, i, left);
        i++;
        left++;
      } else if (nums[i] == 1) {
        i++;
      } else {
        swap(nums, i, right);
        right--;
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    nums[j] = nums[i] - nums[j];
    nums[i] = nums[i] - nums[j];
    nums[j] = nums[i] + nums[j];
  }

  public ArrayList<ArrayList<Integer>> fourSum(int[] numbers, int target) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    if (numbers == null || numbers.length < 4) return result;
    Arrays.sort(numbers);
    for (int i = 0; i < numbers.length - 3; i++) {
      if (i > 0 && numbers[i] == numbers[i - 1]) continue;
      for (int j = i + 1; j < numbers.length - 2; j++) {
        if (j > i + 1 && numbers[j] == numbers[j - 1]) continue;
        int left = j + 1;
        int right = numbers.length - 1;
        while (left < right) {
          int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
          if (sum < target) {
            left++;
          } else if (sum > target) {
            right--;
          } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(numbers[i]);
            list.add(numbers[j]);
            list.add(numbers[left]);
            list.add(numbers[right]);
            result.add(list);
            left++;
            right--;
            while (right > left && numbers[left] == numbers[left - 1]) {
              left++;
            }
            while (right > left && numbers[right] == numbers[right - 1]) {
              right--;
            }
          }
        }
      }
    }

    return result;
  }

  public void wiggleSort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      if (i % 2 == 0 && nums[i] > nums[i - 1] || i % 2 == 1 && nums[i] < nums[i - 1]) {
        swap(nums, i, i - 1);
      }
    }
  }

  static class NumbersComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
      return (s2 + s1).compareTo(s1 + s2);
    }
  }
}
