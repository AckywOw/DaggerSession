package com.ackywow.session.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jiang on 2016/11/23.
 */

public class SequenceId {

  private static final AtomicInteger counter = new AtomicInteger();

  /**
   * 下一个唯一值
   *
   * @return int
   */
  public final static int nextValue() {
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
}
