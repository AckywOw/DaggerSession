package com.ackywow.session.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Jiang on 2016/12/1.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

  protected Activity activity;
  private boolean isAvailable;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activity = this;
    setContentView(getLayoutId());
  }

  /**
   * 获取布局文件ID
   *
   * @return int
   */
  protected abstract int getLayoutId();

  @Override
  protected void onResume() {
    super.onResume();
    isAvailable = true;
  }

  @Override
  protected void onPause() {
    super.onPause();
    isAvailable = false;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  /**
   * 显示loading框
   */
  @Override
  public void showLoadingDialog() {

  }

  /**
   * 消失loading框
   */
  @Override
  public void dismissLoadingDialog() {

  }
}
