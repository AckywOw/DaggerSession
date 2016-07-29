package com.example.easy;

/**
 * 斐波纳契数列
 * 0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
 * Created by AckywOw on 2016/7/29.
 */
public class Fibonacci {
    
    /**
     * 递归
     *
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci1(int n) {
        // write your code here
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        } else {
            return fibonacci1(n - 1) + fibonacci1(n - 2);
        }
    }

    /**
     * 非递归
     *
     * @param n: an integer
     * @return an integer f(n)
     */
    public int fibonacci2(int n) {
        // write your code here
        if (n == 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }
        int f0 = 0, f1 = 1, i = 3, f = 0;
        while (i <= n) {
            f = f0 + f1;
            f0 = f1;
            f1 = f;
            i++;
        }
        return f;
    }
}
