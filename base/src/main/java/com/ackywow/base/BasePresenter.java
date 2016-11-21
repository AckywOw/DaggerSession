package com.ackywow.base;

import android.support.annotation.NonNull;
import rx.Subscription;

/**
 * Created by Jiang on 2016/11/16.
 */
public interface BasePresenter<View extends BaseView> {

  /**
   * 开始执行Task
   */
  void subscribe(@NonNull Subscription subscription);

  /**
   * 解绑所有task
   */
  void unSubscribe();

  /**
   * 获取View
   *
   * @return View
   */
  View getView();

  /**
   * 设置View
   */
  void setView(@NonNull View view);
}
