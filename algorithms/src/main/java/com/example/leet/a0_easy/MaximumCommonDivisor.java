package com.example.leet.a0_easy;

/**
 * 求最大公约数
 * Created by Jiang on 2016/11/1.
 */

public class MaximumCommonDivisor {

    public static void main(String[] args) {
        System.out.println(gcd(7, 11));
    }

    private static int gcd(int p, int q) {
        System.out.println("p:" + p + ", q:" + q);
        if (q == 0) return p;
        int r = p % q;
        return gcd(q, r);
    }
}
