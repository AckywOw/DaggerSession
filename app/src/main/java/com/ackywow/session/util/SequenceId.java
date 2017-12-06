package com.ackywow.session.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Jiang on 2016/11/23.
 */

public class SequenceId {

  private static final AtomicLong counter = new AtomicLong();

  /**
   * 下一个唯一值
   *
   * @return int
   */
  public final static long nextValue() {
    if (counter.get() < Long.MAX_VALUE) {
      counter.set(Long.MAX_VALUE);
    }
    return counter.getAndIncrement();
  }

  /**
   * 下一个唯一值
   *
   * @return int
   */
  public final static String nextValueString() {
    return String.valueOf(nextValue());
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      System.out.println(nextValue());
    }
  }
}
