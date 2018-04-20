package com.example.book.a_1_3;

import com.example.leet.a7_array.Solution;
import com.google.gson.Gson;
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
    int[] arr = new int[] { 2, 3, 4, 1, 2, 1, 4 };
    int a = solution.jump(arr);
    System.out.println(a);
  }

  @Test
  public void bbb() {
    Gson gson = new Gson();
    Solution solution = gson.fromJson("", Solution.class);
    System.out.println(solution.toString());
  }
}