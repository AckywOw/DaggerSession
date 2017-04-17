package com.example.leet.g_more;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mingxxx on 2017/4/17.
 */

public class Solution {

  static String[] LETTERS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

  /**
   * 电话号码的字母组合
   * 给一个不包含01的数字字符串，每个数字代表一个字母，请返回其所有可能的字母组合。
   * 下图的手机按键图，就表示了每个数字可以代表的字母。
   * 样例
   * 给定 "23"
   * 返回 ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
   *
   * @param digits A digital string
   * @return all posible letter combinations
   */
  //时间复杂度3^n===O(2^n)
  public ArrayList<String> letterCombinations(String digits) {
    ArrayList<String> strs = new ArrayList<>();
    if (digits != null
        && digits.trim()
                 .length() > 0) {
      findCombinations(strs, digits, 0, "");
    }
    return strs;
  }

  private void findCombinations(ArrayList<String> strs, String digits, int index, String s) {
    if (index == digits.length()) {
      strs.add(s);
      return;
    }
    char c = digits.charAt(index);
    String temp = LETTERS[c - '0'];
    for (int i = 0; i < temp.length(); i++) {
      findCombinations(strs, digits, index + 1, s + temp.charAt(i));
    }
  }

  /**
   * 给定一个包含不同数字的数组，求出所有可能的排列数组
   *
   * For example,
   * [1,2,3] have the following permutations:
   * [
   * [1,2,3],
   * [1,3,2],
   * [2,1,3],
   * [2,3,1],
   * [3,1,2],
   * [3,2,1]
   * ]
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> lists = new ArrayList<>();
    if (nums != null && nums.length > 0) {
      LinkedList<Integer> list = new LinkedList<>();
      boolean[] marks = new boolean[nums.length];
      findPermute(lists, nums, list, marks);
    }
    return lists;
  }

  private void findPermute(List<List<Integer>> lists, int[] nums, LinkedList<Integer> list,
      boolean[] marks) {
    if (list.size() == nums.length) {
      lists.add(new ArrayList<>(list));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (!marks[i]) {
        list.addLast(nums[i]);
        marks[i] = true;
        findPermute(lists, nums, list, marks);
        list.removeLast();
        marks[i] = false;
      }
    }
  }

  /**
   * 给定一个可能包含相同数字的数组，求出所有可能的排列数组
   *
   * For example,
   * [1,1,2] have the following permutations:
   * [
   * [1,1,2],
   * [1,2,1],
   * [2,1,1]
   * ]
   *
   * @param nums
   * @return
   */
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> lists = new ArrayList<>();
    if (nums != null && nums.length > 0) {
      LinkedList<Integer> list = new LinkedList<>();
      boolean[] marks = new boolean[nums.length];
      Arrays.sort(nums);
      findPermuteUnique(lists, nums, list, marks);
    }
    return lists;
  }

  private void findPermuteUnique(List<List<Integer>> lists, int[] nums, LinkedList<Integer> list,
      boolean[] marks) {
    if (list.size() == nums.length) {
      lists.add(new ArrayList<>(list));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (marks[i]) continue;
      if (i > 0 && nums[i - 1] == nums[i] && marks[i - 1]) continue;
      list.addLast(nums[i]);
      marks[i] = true;
      findPermuteUnique(lists, nums, list, marks);
      list.removeLast();
      marks[i] = false;
    }
  }

  /**
   * 组合
   * 组给出两个整数n和k，返回从1......n中选出的k个数的组合。
   * 样例
   * 例如 n = 4 且 k = 2
   * 返回的解为：
   * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
   *
   * @param n: Given the range of numbers
   * @param k: Given the numbers of combinations
   * @return: All the combinations of k numbers out of 1..n
   */
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> lists = new ArrayList<>();
    if (n > 0 || k > 0 || k > n) {
      LinkedList<Integer> list = new LinkedList<>();
      findCombine(lists, n, k, list, 1);
    }
    return lists;
  }

  private void findCombine(List<List<Integer>> lists, int n, int k, LinkedList<Integer> list,
      int start) {
    if (list.size() == k) {
      lists.add(new ArrayList<>(list));
      return;
    }
    // 还有k - list.size()个空位, 所以,[i...n]中至少要有k-list.size()个元素
    // i最多为 n - (k-list.size()) + 1
    for (int i = start; i <= n - (k - list.size()) + 1; i++) {
      list.addLast(i);
      findCombine(lists, n, k, list, i + 1);
      list.removeLast();
    }
  }
}
