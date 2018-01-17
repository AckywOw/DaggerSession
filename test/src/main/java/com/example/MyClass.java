package com.example;

import org.junit.Test;

public class MyClass {

  @Test
  public void test() {
    String s1 = "s1";
    String s2 = null;
    String s3 = null;
    System.out.println(s1 == null ? s2 == null ? s3 : s2 : s1);
  }
}
