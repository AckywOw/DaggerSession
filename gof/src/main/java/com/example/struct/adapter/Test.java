package com.example.struct.adapter;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class Test {

    public static void main(String[] args) {
        old();
        now();
    }

    public static void old() {
        Old old = new OldImpl();
        old.start();
    }


    public static void now() {
        New now = new Adapter(new OldImpl());
        now.run();
    }
}
