package com.example.book.a_1_3;

import com.example.leet.a7_array.Solution;
import java.util.Arrays;
import org.junit.Test;

/**
 * Created by Jiang on 2016/11/3.
 */
public class TestsTest {
  @Test
  public void main() {
  }

  @Test
  public void aaa() {
    Solution solution = new Solution();
    int[] arr = new int[] { 4, 1, 1, 4, 4, 4, 4, 2, 3, 5 };
    solution.combinationSum2(arr, 10);
    System.out.println(Arrays.toString(arr));
  }
}