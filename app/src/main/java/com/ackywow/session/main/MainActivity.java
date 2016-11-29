package com.ackywow.session.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ackywow.session.MyApp;
import com.ackywow.session.R;
import com.ackywow.session.base.BaseActivity;
import com.ackywow.session.base.CommonPresenter;
import com.ackywow.session.mvp.MvpActivity;
import javax.inject.Inject;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.Activity_Name;

public class MainActivity extends BaseActivity {

  @Bind(R.id.imageView)
  ImageView imageView;

  @Inject
  @Named(Activity_Name)
  String name;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    ((MyApp) getApplication()).getApplicationComponent()
                              .plusMainActivityComponent(new MainActivityModule(this))
                              .inject(this);
    Toast.makeText(this, name, Toast.LENGTH_SHORT)
         .show();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected boolean hasPresenter() {
    return false;
  }

  @NonNull
  @Override
  public CommonPresenter initPresenter() {
    return null;
  }

  @OnClick(R.id.imageView)
  public void onClick() {
    startActivity(new Intent(this, MvpActivity.class));
  }
}
