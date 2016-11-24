package com.ackywow.daggersession.base;

import android.support.annotation.NonNull;
import rx.Subscription;

/**
 * Created by Jiang on 2016/11/16.
 */
public interface BasePresenter<View extends BaseView> {

  /**
   * 添加Task
   */
  void addSubscription(@NonNull Subscription subscription);

  /**
   * 解绑所有task
   */
  void clearSubscriptions();

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
