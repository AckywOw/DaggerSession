package com.example.book.a_2_1;

/**
 * Created by AckywOw on 2016/11/22.
 */

public class Selection extends Sort {

  @Override
  public void sort(Comparable[] arr) {
    int N = arr.length;
    for (int i = 0; i < N - 1; i++) {
      int min = i;
      for (int j = i + 1; j < N; j++) {
        if (less(arr[j], arr[i])) {
          min = j;
        }
      }
      exch(arr, i, min);
    }
  }
}
