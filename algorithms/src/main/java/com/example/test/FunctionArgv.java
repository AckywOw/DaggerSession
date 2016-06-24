package com.example.test;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class FunctionArgv {

    public static void main(String[] args) {
        Num a = new Num(0);
        pps(a);
        System.out.println(a.s);
    }

    private static void pps(Num a) {
        a.s++;
    }

    static class Num {
        int s;

        public Num(int s) {
            this.s = s;
        }
    }
}
