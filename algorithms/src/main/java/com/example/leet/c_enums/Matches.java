package com.example.leet.c_enums;

/**
 * 给你n根火柴棍，你可以拼出多少个形如“A+B=C”的等式？
 * 等式中的A、B、C是用火柴棍拼出的整数（若该数非零，则最高位不能是0）。用火柴棍拼数字0-9的拼法如数组arr所示：
 * Created by AckywOw on 2016/6/9.
 */
public class Matches {
  static final int[] arr = { 6, 2, 5, 5, 4, 5, 6, 3, 7, 6 };

  public static void main(String[] args) {
    int a, b, c, sum, num = 0;
    sum = 20;
    for (a = 0; a < 1111; a++) {
      for (b = 0; b < 1111; b++) {
        c = a + b;
        if (getMatches(a) + getMatches(b) + getMatches(c) == sum - 4) { //4为加号和等号一共所需火柴书
          System.out.println(a + "+" + b + "=" + c);
          num++;
        }
      }
    }
    System.out.println("a" + num + "b");
  }

  /**
   * 计算该数所需火柴数
   *
   * @param num
   * @return
   */
  private static int getMatches(int num) {
    int sum = 0;
    while (num / 10 != 0) {
      sum += arr[num % 10];
      num = num / 10;
    }
    sum += arr[num];
    return sum;
  }
}
