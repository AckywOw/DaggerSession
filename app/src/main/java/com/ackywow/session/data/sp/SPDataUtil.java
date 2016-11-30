package com.ackywow.session.data.sp;

/**
 * Created by Jiang on 2016/11/29.
 */

public class SPDataUtil {

  /**
   * 本地数据初始化
   */
  private static final String INITATA_TIME = "init.data.time";
  private SPUtil spUtil;

  public SPDataUtil(SPUtil spUtil) {
    this.spUtil = spUtil;
  }

  /**
   * 本地存储初始化时间
   *
   * @author: jiang
   */
  public void saveInitataTime() {
    spUtil.putLong(INITATA_TIME, System.currentTimeMillis());
  }

  /**
   * 获取本地存储初始化时间
   *
   * @author: jiang
   */
  public long getInitataTime() {
    return spUtil.getLong(INITATA_TIME, 0);
  }
}
