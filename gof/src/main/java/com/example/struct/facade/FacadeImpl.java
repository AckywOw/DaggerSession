package com.example.struct.facade;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class FacadeImpl implements Facade {
  public static void main(String[] args) {
    new FacadeImpl().test();
  }

  @Override public void a1() {
    new ImplA().a1();
  }

  @Override public void b1() {
    new ImplB().b1();
  }

  @Override public void c1() {
    new ImplC().c1();
  }

  @Override public void test() {
    a1();
    b1();
    c1();
  }
}
