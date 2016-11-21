package com.ackywow.base;

import android.support.annotation.NonNull;

/**
 * Created by Jiang on 2016/11/16.
 */
public interface BaseView<Presenter extends CommonPresenter> {

  /**
   * 初始化Presenter
   *
   * @return Presenter
   */
  @NonNull Presenter initPresenter();

  /**
   * 设置Presenter
   */
  void setPresenter(@NonNull Presenter presenter);

  /**
   * 当前View的UI是否可用
   */
  boolean isAvailable();
}
