package com.ackywow.session.base;

import android.support.annotation.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jiang on 2016/11/16.
 */
public interface BasePresenter<View extends BaseMVPView> {

  /**
   * 添加Subscription
   */
  void addSubscription(@NonNull Disposable disposable);

  /**
   * 解绑所有Subscription
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
