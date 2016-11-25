package com.ackywow.daggersession.base;

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
  @NonNull
  Presenter initPresenter();

  /**
   * 设置Presenter
   */
  void setPresenter(@NonNull Presenter presenter);

  /**
   * 当前View的UI是否可用
   *
   * @return boolean
   */
  boolean isAvailable();

  /**
   * 显示loading框
   */
  void showLoadingDialog();

  /**
   * 消失loading框
   */
  void dismissLoadingDialog();
}
