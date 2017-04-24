package com.example.leet.a0_easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 斐波纳契数列
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 * Created by AckywOw on 2016/7/29.
 */
public class Fibonacci {

  Map<Integer, Integer> map = new HashMap<>();

  /**
   * 递归
   *
   * @param n: an integer
   * @return an integer f(n)
   */
  //时间复杂度O(2^n)
  public int fibonacci1(int n) {
    if (n == 1) {
      return 0;
    } else if (n == 2) {
      return 1;
    }
    return fibonacci1(n - 1) + fibonacci1(n - 2);
  }

  /**
   * 递归(优化: 记忆化搜索 自顶向下解决问题)
   *
   * @param n: an integer
   * @return an integer f(n)
   */
  public int fibonacci2(int n) {//时间复杂度O(n)
    if (n == 1) {
      return 0;
    } else if (n == 2) {
      return 1;
    }
    if (!map.containsKey(n)) {
      map.put(n, fibonacci2(n - 1) + fibonacci2(n - 2));
    }
    return map.get(n);
  }

  /**
   * 无递归(优化: 记忆化搜索 自底向上解决问题)
   *
   * @param n: an integer
   * @return an integer f(n)
   */
  public int fibonacci3(int n) {//时间复杂度O(n)
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    map.put(1, 1);
    for (int i = 2; i <= n; i++) {
      map.put(i, map.get(i - 1) + map.get(i - 2));
    }
    return map.get(n);
  }

  /**
   * 非递归
   *
   * @param n: an integer
   * @return an integer f(n)
   */
  public int fibonacci4(int n) {
    if (n == 1) {
      return 0;
    } else if (n == 2) {
      return 1;
    }
    int f0 = 0, f1 = 1, i = 3, f = 0;
    while (i <= n) {
      f = f0 + f1;
      f0 = f1;
      f1 = f;
      i++;
    }
    return f;
  }
}
