package com.ackywow.daggersession.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jiang on 2016/11/16.
 */
public abstract class BaseActivity<Presenter extends CommonPresenter> extends AppCompatActivity
    implements BaseView<Presenter> {
  protected final String TAG = getClass().getSimpleName();
  protected Activity activity;
  protected Presenter presenter;
  private boolean isAvailable;

  @Override
  public void showLoadingDialog() {

  }

  @Override
  public void dismissLoadingDialog() {

  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity = this;
    setContentView(getLayoutId());
    if (hasPresenter()) {
      setPresenter(initPresenter());
    }
  }

  /**
   * 获取布局文件ID
   *
   * @return int
   */
  protected abstract int getLayoutId();

  /**
   * 是否需要Presenter
   *
   * @return boolean
   */
  protected abstract boolean hasPresenter();

  @Override
  public void setPresenter(@NonNull Presenter presenter) {
    this.presenter = checkNotNull(presenter, presenter.getClass()
                                                      .getName() + " cannot be " +
        "null!");
    this.presenter.setView(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    isAvailable = true;
  }

  @Override
  protected void onPause() {
    super.onPause();
    isAvailable = false;
    if (hasPresenter()) {
      presenter.clearSubscriptions();
    }
  }

  @Override
  public boolean isAvailable() {
    return isAvailable;
  }
}
