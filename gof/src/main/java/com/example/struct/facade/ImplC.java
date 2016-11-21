package com.example.struct.facade;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class ImplC implements C {
  @Override public void c1() {

    System.out.println("outC");
  }

  @Override public void c2() {

    System.out.println("inC");
  }
}
