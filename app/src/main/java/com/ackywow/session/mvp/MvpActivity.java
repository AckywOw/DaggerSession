package com.ackywow.session.mvp;

import android.support.annotation.NonNull;
import android.widget.Toast;
import com.ackywow.session.R;

public class MvpActivity extends MVPContact.View {

  @Override
  void showErrowDialog() {
    Toast.makeText(activity, "error", Toast.LENGTH_SHORT)
         .show();
  }

  @Override
  void showToast(String str) {
    Toast.makeText(activity, str, Toast.LENGTH_SHORT)
         .show();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_mvp;
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
