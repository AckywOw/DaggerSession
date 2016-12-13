package com.example.leet.a5_string;

import java.util.HashSet;

/**
 * Created by AckywOw on 2016/8/1.
 */
public class Solution {
  /**
   * 最长无重复字符的子串
   * 给定一个字符串，请找出其中无重复字符的最长子字符串。
   *
   * 例如，在"abcabcbb"中，其无重复字符的最长子字符串是"abc"，其长度为 3。
   * 对于，"bbbbb"，其无重复字符的最长子字符串为"b"，长度为1。
   * @param s: a string
   * @return: an integer
   */
  public int lengthOfLongestSubstring(String s) {
    // write your code here
    if (s == null && s.length() == 0) {
      return 0;
    }
    HashSet<Character> set = new HashSet<Character>();
    int leftBound = 0, max = 0;
    for (int i = 0; i < s.length(); i++) {
      if (set.contains(s.charAt(i))) {
        while (leftBound < i && s.charAt(leftBound) != s.charAt(i)) {
          set.remove(s.charAt(leftBound));
          leftBound++;
        }
        leftBound++;
      } else {
        set.add(s.charAt(i));
        max = Math.max(max, i - leftBound + 1);
      }
    }

    return max;
  }

  /**
   * 比较字符串
   * 比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母
   * 样例
   * 给出 A = "ABCD" B = "ACD"，返回 true
   * 给出 A = "ABCD" B = "AABC"， 返回 false
   *
   * @param A : A string includes Upper Case letters
   * @param B : A string includes Upper Case letter
   * @return :  if string A contains all of the characters in B return true else return false
   */
  public boolean compareStrings(String A, String B) {
    // write your code here
    int[] counts = new int[26];
    for (int i = 0; i < 26; i++) {
      counts[i] = 0;
    }
    for (int i = 0; i < A.length(); i++) {
      counts[A.charAt(i) - 'A']++;
    }
    for (int i = 0; i < B.length(); i++) {
      counts[B.charAt(i) - 'A']--;
      if (counts[B.charAt(i) - 'A'] < 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * 判断字符串是否没有重复字符
   * 实现一个算法确定字符串中的字符是否均唯一出现
   *
   * @param str: a string
   * @return: a boolean
   */
  public boolean isUnique(String str) {
    // write your code here
    boolean[] char_set = new boolean[256];
    for (int i = 0; i < str.length(); i++) {
      int val = str.charAt(i);
      if (char_set[val]) return false;
      char_set[val] = true;
    }
    return true;
  }

  /**
   * 给k个字符串，求出他们的最长公共前缀(LCP)
   *
   * @param strs: A list of strings
   * @return: The longest common prefix
   */
  public String longestCommonPrefix(String[] strs) {
    // write your code here
    if (strs == null || strs.length == 0) {
      return "";
    }
    String prefix = strs[0];
    for (int i = 1; i < strs.length; i++) {
      int j = 0;
      while (j < strs[i].length() && j < prefix.length() && strs[i].charAt(j) == prefix.charAt(j)) {
        j++;
      }
      if (j == 0) {
        return "";
      }
      prefix = prefix.substring(0, j);
    }
    return prefix;
  }

  /**
   * 对于一个给定的 source 字符串和一个 target 字符串，你应该在 source 字符串中找出 target 字符串出现的第一个位置(从0开始)。如果不存在，则返回 -1
   * Returns a index to the first occurrence of target in source,
   * or -1  if target is not part of source.
   *
   * @param source string to be scanned.
   * @param target string containing the sequence of characters to match.
   */
  public int strStr(String source, String target) {
    //write your code here
    if (source == null || target == null) {
      return -1;
    }

    for (int i = 0; i < source.length() - target.length() + 1; i++) {
      int j = 0;
      for (j = 0; j < target.length(); j++) {
        if (source.charAt(i + j) != target.charAt(j)) {
          break;
        }
      }
      // finished loop, target found
      if (j == target.length()) {
        return i;
      }
    }
    return -1;
  }

  /**
   * 给出两个字符串，找到最长公共子串，并返回其长度。
   *
   * @param A, B: Two string.
   * @return: the length of the longest common substring.
   */
  public int longestCommonSubstring(String A, String B) {
    // write your code here
    int maxlen = 0;
    int xlen = A.length();
    int ylen = B.length();
    for (int i = 0; i < xlen; ++i) {
      for (int j = 0; j < ylen; ++j) {
        int len = 0;
        while (i + len < xlen && j + len < ylen && A.charAt(i + len) == B.charAt(j + len)) {
          len++;
          if (len > maxlen) maxlen = len;
        }
      }
    }
    return maxlen;
  }
}
