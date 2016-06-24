package com.example.struct.facade;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class ImplA implements A {
    @Override
    public void a1() {
        System.out.println("outA");
    }

    @Override
    public void a2() {
        System.out.println("inA");
    }
}
