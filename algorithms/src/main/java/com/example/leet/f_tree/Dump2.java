package com.example.leet.f_tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by AckywOw on 2016/6/21.
 */
public class Dump2 {

  public static void main(String[] args) {
    Integer[] nums = { 99, 5, 36, 7, 22, 17, 92, 12, 2, 19, 25, 28, 1, 46 };
    LinkedList<Integer> heap = new LinkedList<Integer>(Arrays.asList(nums));
    creatHeap(heap);
    System.out.println(heap.toString());

    List<Integer> sortHeap = sortHeap(heap);
    System.out.println(sortHeap.toString());
  }

  private static List<Integer> sortHeap(LinkedList<Integer> heap) {
    List<Integer> sortHeap = new ArrayList<>();
    while (heap.size() > 0) {
      swap(heap, 0, heap.size() - 1);
      sortHeap.add(0, heap.getLast());
      heap.removeLast();
      siftUp(heap, 0);
    }
    return sortHeap;
  }

  private static void creatHeap(LinkedList<Integer> heap) {
    for (int i = heap.size() / 2; i >= 0; i--) {
      siftUp(heap, i);
    }
  }

  private static void siftUp(LinkedList<Integer> heap, int i) {
    int t;
    boolean ok = false;
    while (i * 2 + 1 < heap.size() && !ok) {
      t = heap.get(i) < heap.get(i * 2 + 1) ? i * 2 + 1 : i;
      if (i * 2 + 2 < heap.size() && heap.get(t) < heap.get(i * 2 + 2)) {
        t = i * 2 + 2;
      }
      if (t != i) {
        swap(heap, i, t);
        i = t;
      } else {
        ok = true;
      }
    }
  }

  private static void swap(LinkedList<Integer> arr, int i, int j) {
    Collections.swap(arr, i, j);
  }
}
