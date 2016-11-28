package com.ackywow.daggersession.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class BaseFragment<Presenter extends CommonPresenter> extends Fragment
    implements BaseView<Presenter> {

  protected final String TAG = getClass().getSimpleName();

  protected Activity activity;

  protected Presenter presenter;

  private boolean isAvailable;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    activity = (Activity) context;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState == null) {
      restoreSaveInstanceState(getArguments());
    } else {
      restoreSaveInstanceState(savedInstanceState);
    }
  }

  protected abstract void restoreSaveInstanceState(Bundle bundle);

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = View.inflate(activity, getLayoutId(), null);
    initView(view);
    registListener();
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
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
   * 初始化View
   */
  protected abstract void initView(View view);

  /**
   * 注册监听
   */
  protected void registListener() {
  }

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
  public void onResume() {
    super.onResume();
    isAvailable = true;
  }

  @Override
  public void onPause() {
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
