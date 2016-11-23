package com.example.book.a_2_1;

/**
 * Created by AckywOw on 2016/11/22.
 */

public class Insertion extends Sort {

  @Override
  public void sort(Comparable[] arr) {
    int N = arr.length;
    for (int i = 1; i < N; i++) {
      for (int j = i; j > 0 && less(arr[j], arr[j - 1]); j--) {
        exch(arr, j, j - 1);
      }
    }
  }
}
