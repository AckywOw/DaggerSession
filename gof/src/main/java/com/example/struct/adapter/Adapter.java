package com.example.struct.adapter;

/**
 * Created by AckywOw on 2016/6/12.
 */
public class Adapter implements New {

  private Old mOld;

  public Adapter(Old old) {
    mOld = old;
  }

  @Override
  public void run() {
    mOld.start();
    System.out.println("new");
  }
}
