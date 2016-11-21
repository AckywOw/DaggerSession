package com.example.create.simplefactory;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class Test {
  public static void main(String[] args) {
    Api api = Factory.createApi();
    api.test();
  }
}
