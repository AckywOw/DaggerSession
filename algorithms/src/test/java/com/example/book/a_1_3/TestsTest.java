package com.example.book.a_1_3;

import java.util.HashMap;
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
  }

  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int i = 0, size = nums.length; i < size; i++) {
      if (map.containsKey(nums[i])) {
        return new int[] { map.get(nums[i]), i };
      } else {
        map.put(target - nums[i], i);
      }
    }
    return null;
  }
}