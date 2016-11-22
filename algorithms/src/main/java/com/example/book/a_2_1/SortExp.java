package com.example.book.a_2_1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by AckywOw on 2016/11/22.
 */

public class SortExp {

  public static final String PATH = "D:\\algs4-data\\words3.txt";

  public static void main(String[] args) {
    String[] arr = new In(PATH).readAllStrings();
    Sort sort = new Insertion();
    sort.sort(arr);
    assert isSorted(arr);
    show(arr);
  }

  /**
   * 是否小于
   */
  protected static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  /**
   * 交换
   */
  protected static void exch(Comparable[] arr, int i, int j) {
    Comparable t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }

  /**
   * 打印
   */
  protected static void show(Comparable[] arr) {
    for (int i = 0; i < arr.length; i++) {
      StdOut.print(arr[i] + " ");
    }
    StdOut.println();
  }

  /**
   * 判断排序
   */
  protected static boolean isSorted(Comparable[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      if (less(arr[i + 1], arr[i])) return false;
    }
    return true;
  }
}
