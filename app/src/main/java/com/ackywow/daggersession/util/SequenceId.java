package com.ackywow.daggersession.util;

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
  public static int nextValue() {
    return counter.getAndIncrement();
  }
}
