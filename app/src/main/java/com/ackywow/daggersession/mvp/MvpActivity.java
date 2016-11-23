package com.ackywow.daggersession.mvp;

import android.support.annotation.NonNull;
import com.ackywow.daggersession.R;

public class MvpActivity extends MVPContact.View {

  @Override
  void showErrowDialog() {

  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_mvp;
  }

  @Override
  protected boolean hasPresenter() {
    return true;
  }

  @NonNull
  @Override
  public MVPContact.Presenter initPresenter() {
    return new MvpPresenter();
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.loadNetDate();
  }
}
