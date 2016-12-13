package com.example.leet.a6_hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jiang on 2016/12/13.
 */

public class Solution {
  /**
   * 最多有多少个点在一条直线上
   * 给出二维平面上的n个点，求最多有多少点在同一条直线上。
   *
   * 给出4个点：(1, 2), (3, 6), (0, 0), (1, 3)。
   * 一条直线上的点最多有3个。
   *
   * @param points an array of point
   * @return an integer
   */
  public int maxPoints(Point[] points) {
    if (points == null || points.length == 0) {
      return 0;
    }

    HashMap<Double, Integer> map = new HashMap<Double, Integer>();
    int max = 1;

    for (int i = 0; i < points.length; i++) {
      // shared point changed, map should be cleared and server the new point
      map.clear();

      // maybe all points contained in the list are same points,and same points' k is
      // represented by Integer.MIN_VALUE
      map.put((double) Integer.MIN_VALUE, 1);

      int dup = 0;
      for (int j = i + 1; j < points.length; j++) {
        if (points[j].x == points[i].x && points[j].y == points[i].y) {
          dup++;
          continue;
        }

        // look 0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x)
        // because (double)0/-1 is -0.0, so we should use 0.0+-0.0=0.0 to solve 0.0 !=-0.0
        // problem

        // if the line through two points are parallel to y coordinator, then K(slop) is
        // Integer.MAX_VALUE
        double key = points[j].x - points[i].x == 0 ? Integer.MAX_VALUE
            : 0.0 + (double) (points[j].y - points[i].y) / (double) (points[j].x - points[i].x);

        if (map.containsKey(key)) {
          map.put(key, map.get(key) + 1);
        } else {
          map.put(key, 2);
        }
      }

      for (int temp : map.values()) {
        // duplicate may exist
        if (temp + dup > max) {
          max = temp + dup;
        }
      }
    }
    return max;
  }

  /**
   * 乱序字符串
   * 给出一个字符串数组S，找到其中所有的乱序字符串(Anagram)。如果一个字符串是乱序字符串，那么他存在一个字母集合相同，但顺序不同的字符串也在S中。
   * 所有的字符串都只包含小写字母
   *
   * 对于字符串数组 ["lint","intl","inlt","code"]
   * 返回 ["lint","inlt","intl"]
   *
   * @param strs: A list of strings
   * @return: A list of strings
   */
  public List<String> anagrams(String[] strs) {
    ArrayList<String> result = new ArrayList<String>();
    HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

    for (String str : strs) {
      int[] count = new int[26];
      for (int i = 0; i < str.length(); i++) {
        count[str.charAt(i) - 'a']++;
      }

      int hash = getHash(count);
      if (!map.containsKey(hash)) {
        map.put(hash, new ArrayList<String>());
      }

      map.get(hash)
         .add(str);
    }

    for (ArrayList<String> tmp : map.values()) {
      if (tmp.size() > 1) {
        result.addAll(tmp);
      }
    }

    return result;
  }

  private int getHash(int[] count) {
    int hash = 0;
    int a = 378551;
    int b = 63689;
    for (int num : count) {
      hash = hash * a + num;
      a = a * b;
    }
    return hash;
  }

  /**
   * 快乐数
   * 写一个算法来判断一个数是不是"快乐数"。
   * 一个数是不是快乐是这么定义的：对于一个正整数，每一次将该数替换为他每个位置上的数字的平方和，
   * 然后重复这个过程直到这个数变为1，或是无限循环但始终变不到1。如果可以变为1，那么这个数就是快乐数。
   *
   * 19 就是一个快乐数。
   * 1^2 + 9^2 = 82
   * 8^2 + 2^2 = 68
   * 6^2 + 8^2 = 100
   * 1^2 + 0^2 + 0^2 = 1
   *
   * @param n an integer
   * @return true if this is a happy number or false
   */
  public boolean isHappy(int n) {
    HashSet<Integer> hash = new HashSet<Integer>();
    while (n != 1) {
      if (hash.contains(n)) {
        return false;
      }
      hash.add(n);
      n = getNextHappy(n);
    }
    return true;
  }

  private int getNextHappy(int n) {
    int sum = 0;
    while (n != 0) {
      sum += (n % 10) * (n % 10);
      n /= 10;
    }
    return sum;
  }

  /**
   * 最小子串覆盖
   * 给定一个字符串source和一个目标字符串target，在字符串source中找到包括所有目标字符串字母的子串。
   * 如果在source中没有这样的子串，返回""，如果有多个这样的子串，返回起始位置最小的子串。
   *
   * 在答案的子串中的字母在目标字符串中是否需要具有相同的顺序？
   * ——不需要。
   * 样例
   * 给出source = "ADOBECODEBANC"，target = "ABC" 满足要求的解  "BANC"
   *
   * @param source: A string
   * @param target: A string
   * @return: A string denote the minimum window
   * Return "" if there is no such a string
   */
  public String minWindow(String source, String target) {
    // queueing the position that matches the char in T
    int ans = Integer.MAX_VALUE;
    String minStr = "";

    int[] sourcehash = new int[256];
    int[] targethash = new int[256];

    initTargetHash(targethash, target);
    int j = 0, i = 0;
    for (i = 0; i < source.length(); i++) {
      while (!valid(sourcehash, targethash) && j < source.length()) {
        sourcehash[source.charAt(j)]++;
        if (j < source.length()) {
          j++;
        } else {
          break;
        }
      }
      if (valid(sourcehash, targethash)) {
        if (ans > j - i) {
          ans = Math.min(ans, j - i);
          minStr = source.substring(i, j);
        }
      }
      sourcehash[source.charAt(i)]--;
    }
    return minStr;
  }

  int initTargetHash(int[] targethash, String Target) {
    int targetnum = 0;
    for (char ch : Target.toCharArray()) {
      targetnum++;
      targethash[ch]++;
    }
    return targetnum;
  }

  boolean valid(int[] sourcehash, int[] targethash) {

    for (int i = 0; i < 256; i++) {
      if (targethash[i] > sourcehash[i]) return false;
    }
    return true;
  }

  class Point {
    int x;
    int y;

    Point() {
      x = 0;
      y = 0;
    }

    Point(int a, int b) {
      x = a;
      y = b;
    }
  }
}
