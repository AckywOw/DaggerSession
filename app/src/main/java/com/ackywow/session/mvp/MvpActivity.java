package com.ackywow.session.mvp;

import android.widget.Toast;
import com.ackywow.session.R;

public class MvpActivity extends MvpContact.View<MvpPresenter, MvpComponet> {

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
  protected void initComponent() {
    component = activityComponent.newMvpComponet();
  }

  @Override
  protected void inject() {
    component.inject(this);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_mvp;
  }

  @Override
  protected void onResume() {
    super.onResume();
    presenter.loadNetDate();
  }
}
