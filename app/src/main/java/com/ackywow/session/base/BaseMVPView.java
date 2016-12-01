package com.ackywow.session.base;

import android.support.annotation.NonNull;

/**
 * Created by Jiang on 2016/11/16.
 */
public interface BaseMVPView<Presenter extends CommonPresenter> {

  /**
   * 设置Presenter
   */
  void setPresenter(@NonNull Presenter presenter);
}
