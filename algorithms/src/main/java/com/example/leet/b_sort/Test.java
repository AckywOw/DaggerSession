package com.example.leet.b_sort;

import com.example.book.a_1_3.Node;
import java.util.Arrays;
import java.util.Random;

import static com.example.leet.b_sort.Solution.sortNode;
import static com.example.leet.b_sort.Sort.quickSort;

/**
 * Created by Jiang on 2016/12/1.
 */

public class Test {
  public static void main(String[] args) {
    //arraySort();
    linkedSort();
  }

  private static void arraySort() {
    int[] nums = new int[10];
    Random random = new Random();
    for (int i = 0; i < nums.length; i++) {
      nums[i] = random.nextInt(100) + 1;
    }

    System.out.println(Arrays.toString(nums));
    quickSort(nums);
    System.out.println(Arrays.toString(nums));
  }

  private static void linkedSort() {
    Random random = new Random();
    Node<Integer> first = null;
    Node<Integer> now = null;
    for (int i = 0; i < 10; i++) {
      Node<Integer> temp = new Node<>();
      temp.item = random.nextInt(100) + 1;
      if (first == null) {
        first = temp;
        now = first;
      } else {
        now.next = temp;
        now = temp;
      }
    }

    System.out.println(first);
    first = sortNode(first);
    System.out.println(first);
  }
}
