package com.example.leet.e_shortestpath;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态规划
 * Created by mingxxx on 2017/4/20.
 */

public class Solution {
  Map<Integer, Integer> map = new HashMap<>();

  //----------------------------------------------------------------
  //时间复杂度O(A * m)
  //空间复杂度O(A * m)
  public static int backPackII2(int m, int[] A, int V[]) {
    if (A.length == 0 || m == 0) return 0;
    int[][] arrs = new int[A.length][m + 1];
    for (int i = 0; i <= m; i++) {
      if (i >= A[0]) {
        arrs[0][i] = V[0];
      }
    }
    for (int i = 1; i < A.length; i++) {
      for (int j = 0; j <= m; j++) {
        arrs[i][j] = arrs[i - 1][j];
        if (j >= A[i]) {
          arrs[i][j] = Math.max(arrs[i][j], V[i] + arrs[i - 1][j - A[i]]);
        }
      }
    }
    return arrs[A.length - 1][m];
  }

  //时间复杂度O(A * m)
  //空间复杂度O(2 * m)
  public static int backPackII3(int m, int[] A, int V[]) {
    if (A.length == 0 || m == 0) return 0;
    int[][] arrs = new int[2][m + 1];
    for (int i = 0; i <= m; i++) {
      if (i >= A[0]) {
        arrs[0][i] = V[0];
      }
    }
    for (int i = 1; i < A.length; i++) {
      for (int j = 0; j <= m; j++) {
        arrs[i % 2][j] = arrs[(i - 1) % 2][j];
        if (j >= A[i]) {
          arrs[i % 2][j] = Math.max(arrs[i % 2][j], V[i] + arrs[(i - 1) % 2][j - A[i]]);
        }
      }
    }
    return arrs[(A.length - 1) % 2][m];
  }

  //时间复杂度O(A * m)
  //空间复杂度O(m)
  public static int backPackII4(int m, int[] A, int V[]) {
    if (A.length == 0 || m == 0) return 0;
    int[] arrs = new int[m + 1];
    for (int i = 0; i <= m; i++) {
      if (i >= A[0]) {
        arrs[i] = V[0];
      }
    }
    for (int i = 1; i < A.length; i++) {
      for (int j = m; j >= A[i]; j--) {
        arrs[j] = Math.max(arrs[j], V[i] + arrs[j - A[i]]);
      }
    }
    return arrs[m];
  }

  /**
   * 给定一个正整数数组，能否把它分成两部分，每部分的和是相等的？
   * 每个数字最大是100
   * 数组最多200个元素
   *
   * @param nums
   * @return
   */
  public static boolean canPartition(int[] nums) {
    if (nums == null || nums.length <= 1) return false;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    if (sum % 2 == 1) return false;
    //背包个数nums.length, 背包大小sum/2
    sum /= 2;
    boolean[] arr = new boolean[sum + 1];
    for (int i = 0; i <= sum; i++) {
      arr[i] = i == nums[0];
    }
    for (int i = 1; i < nums.length; i++) {
      for (int j = sum; j >= nums[i]; j--) {
        arr[j] = arr[j] || arr[j - nums[i]];
      }
    }
    return arr[sum];
  }

  public static boolean canPartition2(int[] nums) {
    if (nums == null || nums.length <= 1) return false;
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
    }
    if (sum % 2 == 1) return false;
    //背包个数nums.length, 背包大小sum/2
    int[][] arrs = new int[nums.length][sum / 2 + 1];
    for (int i = 0; i < arrs.length; i++) {
      for (int j = 0; j < arrs[i].length; j++) {
        arrs[i][j] = -1;
      }
    }
    return tryPut(arrs, nums, nums.length - 1, sum / 2);
  }

  private static boolean tryPut(int[][] arrs, int[] nums, int index, int sum) {
    if (sum == 0) return true;
    if (index < 0 || sum < 0) return false;
    if (arrs[index][sum] > 0) return arrs[index][sum] == 1;
    arrs[index][sum] =
        (tryPut(arrs, nums, index - 1, sum) || tryPut(arrs, nums, index - 1, sum - nums[index])) ? 1
            : -1;
    return arrs[index][sum] == 1;
  }

  /**
   * 最长上升子序列
   * 给定一个整数序列，找到最长上升子序列（LIS），返回LIS的长度。
   * 说明
   * 最长上升子序列的定义：
   * 最长上升子序列问题是在一个无序的给定序列中找到一个尽可能长的由低到高排列的子序列，这种子序列不一定是连续的或者唯一的。
   * https://en.wikipedia.org/wiki/Longest_increasing_subsequence
   * 样例
   * 给出 [5,4,1,2,3]，LIS 是 [1,2,3]，返回 3
   * 给出 [4,2,4,5,3,7]，LIS 是 [2,4,5,7]，返回 4
   *
   * @param nums
   * @return
   */
  public static int lengthOfLIS(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int[] arr = new int[nums.length];
    for (int i = 0; i < nums.length; i++) {
      arr[i] = 1;
    }
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
      for (int j = i - 1; j >= arr[i] - 1; j--) {
        if (nums[i] > nums[j]) {
          arr[i] = Math.max(arr[i], 1 + arr[j]);
          max = Math.max(max, arr[i]);
        }
      }
    }
    return max;
  }

  public static int lengthOfLIS2(int[] nums) {
    if (nums == null || nums.length == 0) return 0;
    int[] arrs = new int[nums.length];
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
      max = Math.max(max, getIt(nums, i, arrs));
    }
    return max;
  }

  private static int getIt(int[] nums, int index, int[] arrs) {
    if (arrs[index] > 0) return arrs[index];
    int max = 1;
    for (int i = 0; i < index; i++) {
      if (nums[i] < nums[index]) {
        max = Math.max(max, 1 + getIt(nums, i, arrs));
      }
    }
    arrs[index] = max;
    return max;
  }

  /**
   * 最长公共子序列
   * 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度。
   * 说明
   * 最长公共子序列的定义：
   * 最长公共子序列问题是在一组序列（通常2个）中找到最长公共子序列（注意：不同于子串，LCS不需要是连续的子串）。该问题是典型的计算机科学问题，是文件差异比较程序的基础，在生物信息学中也有所应用。
   * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
   * 样例
   * 给出"ABCD" 和 "EDCA"，这个LCS是 "A" (或 D或C)，返回1
   * 给出 "ABCD" 和 "EACB"，这个LCS是"AC"返回 2
   *
   * @param A, B: Two strings.
   * @return: The length of longest common subsequence of A and B.
   */
  public static int longestCommonSubsequence(String A, String B) {
    if (A.length() == 0 || B.length() == 0) return 0;
    int[][] arrs = new int[A.length()][B.length()];
    return lcs(A, B, A.length() - 1, B.length() - 1, arrs);
  }

  private static int lcs(String A, String B, int aIndex, int bIndex, int[][] arrs) {
    if (aIndex < 0 || bIndex < 0) return 0;
    if (arrs[aIndex][bIndex] > 0) return arrs[aIndex][bIndex];
    int max;
    if (A.charAt(aIndex) == B.charAt(bIndex)) {
      max = 1 + lcs(A, B, aIndex - 1, bIndex - 1, arrs);
    } else {
      max = Math.max(lcs(A, B, aIndex - 1, bIndex, arrs), lcs(A, B, aIndex, bIndex - 1, arrs));
    }
    arrs[aIndex][bIndex] = max;
    return max;
  }

  public static int longestCommonSubsequence2(String A, String B) {
    if (A.length() == 0 || B.length() == 0) return 0;
    int[][] arrs = new int[A.length()][B.length()];
    for (int i = 0; i < A.length(); i++) {
      if (A.charAt(i) == B.charAt(0)) {
        arrs[i][0] = 1;
      }
    }
    for (int i = 0; i < B.length(); i++) {
      if (A.charAt(0) == B.charAt(i)) {
        arrs[0][i] = 1;
      }
    }
    for (int i = 1; i < A.length(); i++) {
      for (int j = 1; j < B.length(); j++) {
        if (A.charAt(i) == B.charAt(i)) {
          arrs[i][j] = arrs[i - 1][j - 1] + 1;
        } else {
          arrs[i][j] = Math.max(arrs[i - 1][j], arrs[i][j - 1]);
        }
      }
    }
    return arrs[A.length() - 1][B.length() - 1];
  }

  public static void main(String[] args) {
    //int[] A = { 10, 4, 2, 4, 5, 3, 7, 1 };
    String A = "ABCD";
    String B = "EACB";

    System.out.println(longestCommonSubsequence2(A, B));
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
    if (n == 0) return 0;
    if (n == 1) return 1;
    int[] arr = new int[n + 1];
    arr[0] = 0;
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
  }

  /**
   * 爬楼梯
   * 假设你正在爬楼梯，需要n步你才能到达顶部。但每次你只能爬一步或者两步，你能有多少种不同的方法爬到楼顶部？
   * 样例
   * 比如n=3，1+1+1=1+2=2+1=3，共有3中不同的方法
   * 返回 3
   *
   * @param n
   * @return
   */
  public int climbStairs(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    int[] arr = new int[n + 1];
    arr[1] = 1;
    arr[2] = 2;
    for (int i = 3; i <= n; i++) {
      arr[i] = arr[i - 1] + arr[i - 2];
    }
    return arr[n];
  }

  /**
   * Integer Break
   * 给定一个正整数n，把它分割成至少两个正整数的和，并且求出他们的乘积，返回你能得到的最大的乘积。
   * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
   * Note: You may assume that n is not less than 2 and not larger than 58.
   *
   * @param n
   * @return
   */
  public int integerBreak(int n) {
    int[] arr = new int[n + 1];
    arr[1] = 1;
    for (int i = 2; i <= n; i++) {
      for (int j = 1; j < i; j++) {
        arr[i] = Math.max(Math.max(j * arr[i - j], j * (i - j)), arr[i]);
      }
    }
    return arr[n];
  }

  /**
   * 打劫房屋
   * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。每个房子都存放着特定金额的钱。
   * 你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
   * 给定一个非负整数列表，表示每个房子中存放的钱，算一算，如果今晚去打劫，你最多可以得到多少钱 在不触动报警装置的情况下。
   * 样例
   * 给定 [3, 8, 4], 返回 8.
   *
   * @param nums
   * @return
   */
  public int rob(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int[] arr = new int[nums.length]; //定义状态
    arr[0] = nums[0];
    arr[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      for (int j = i - 2; j >= 0; j--) { //状态的转移
        arr[i] = Math.max(Math.max(arr[i - 1], arr[i - 2] + nums[i]), arr[i]);
      }
    }
    return arr[nums.length - 1];
  }

  public int rob2(int[] nums) {
    int prewS = 0;
    int prewN = 0;
    for (int n : nums) {
      int temp = prewN;
      prewN = Math.max(prewS, prewN);
      prewS = temp + n;
    }
    return Math.max(prewS, prewN);
  }

  /**
   * 背包问题 II
   * 给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？
   * A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的m。
   * 样例
   * 对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
   *
   * @param m: An integer m denotes the size of a backpack
   * @param A & V: Given n items with size A[i] and value V[i]
   * @return: The maximum value
   */
  public int backPackII(int m, int[] A, int V[]) {
    if (A.length == 0 || m == 0) return 0;
    int[][] arrs = new int[A.length][m + 1];
    for (int i = 0; i < arrs.length; i++) {
      for (int j = 0; j < arrs[i].length; j++) {
        arrs[i][j] = -1;
      }
    }
    return maxValue(arrs, A, V, A.length - 1, m);
  }

  private int maxValue(int[][] arrs, int[] A, int[] V, int index, int m) {
    if (index < 0 || m <= 0) return 0;
    if (arrs[index][m] != -1) {
      return arrs[index][m];
    }
    int max = maxValue(arrs, A, V, index - 1, m);
    if (A[index] <= m) {
      max = Math.max(max, V[index] + maxValue(arrs, A, V, index - 1, m - A[index]));
    }
    arrs[index][m] = max;
    return max;
  }
}
