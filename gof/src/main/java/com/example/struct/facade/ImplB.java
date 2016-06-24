package com.example.struct.facade;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class ImplB implements B {

    @Override
    public void b1() {

        System.out.println("outB");
    }

    @Override
    public void b2() {

        System.out.println("inB");
    }
}
