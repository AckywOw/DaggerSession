package com.example.leet.g_more;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by mingxxx on 2017/4/25.
 */

public class Greedy {
  /**
   * 分配饼干
   * 有一组饼干，每个饼干的值为s(i)，有一组小朋友，每个人都有一个贪心指数g(i)。
   * 如果s(j)>=g(i)，即将饼干j分配给小朋友j，则他就们满足。问怎么分配才能让更多的小朋友开心。
   * 如g=[1,2,3],s=[1,1], 结果是1
   * 如g=[1,2],s=[1,2,3], 结果是2
   *
   * @param g
   * @param s
   * @return
   */
  public static int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g);//升序排列
    Arrays.sort(s);
    int gIndex = g.length - 1;
    int sIndex = s.length - 1;
    int num = 0;
    while (sIndex >= 0 && gIndex >= 0) {
      if (s[sIndex] >= g[gIndex]) {
        sIndex--;
        gIndex--;
        num++;
      } else {
        gIndex--;
      }
    }
    return num;
  }

  /**
   * 给定一组区间，问最少删除多少个区间，可以让这些区间之间互相不重叠
   * 给定的区间都是起始点小于终止点
   * 重叠的定义是不包含起始点相同的情况，如[1,2]和[2,3]不算重叠
   *
   * @param intervals
   * @return
   */
  //动态规划方法O(n^2)
  public static int eraseOverlapIntervals(Interval[] intervals) {
    if (intervals.length == 0) return 0;
    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval a, Interval b) { //先按照start升序
        if (a.start != b.start) return a.start - b.start;
        return a.end - b.end;
      }
    });
    //arr[i]记录的是到第i个区间时，最多的不重叠区间的个数
    int[] arr = new int[intervals.length];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = 1;
    }
    int max = 1;
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        if (intervals[i].start >= intervals[j].end) {
          arr[i] = Math.max(arr[i], 1 + arr[j]);
          max = Math.max(arr[i], max);
        }
      }
    }
    return intervals.length - max;
  }

  public static void main(String[] args) {
    Interval[] intervals = new Interval[3];
    intervals[0] = new Interval(1, 3);
    intervals[1] = new Interval(2, 3);
    intervals[2] = new Interval(2, 4);
    intervals[2] = new Interval(0, 5);
    System.out.println(eraseOverlapIntervals(intervals));
  }

  //贪心算法O(n)
  public int eraseOverlapIntervals2(Interval[] intervals) {
    if (intervals.length == 0) return 0;
    Arrays.sort(intervals, new Comparator<Interval>() {
      @Override
      public int compare(Interval a, Interval b) { //先按照end升序
        if (a.end != b.end) return a.end - b.end;
        return a.start - b.start;
      }
    });

    int max = 1;
    int pre = 0;
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i].start >= intervals[pre].end) {
        max++;
        pre = i;
      }
    }
    return intervals.length - max;
  }

  public static class Interval {
    int start;
    int end;

    Interval() {
      start = 0;
      end = 0;
    }

    Interval(int s, int e) {
      start = s;
      end = e;
    }
  }
}
