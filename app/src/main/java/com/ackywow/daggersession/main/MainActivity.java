package com.ackywow.daggersession.main;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ackywow.daggersession.MyApp;
import com.ackywow.daggersession.R;
import com.ackywow.daggersession.base.BaseActivity;
import com.ackywow.daggersession.base.CommonPresenter;
import com.ackywow.daggersession.inject.Ball;
import com.ackywow.daggersession.mvp.MvpActivity;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static com.ackywow.daggersession.constant.Nameds.Activity_Name;

public class MainActivity extends BaseActivity {

  @Inject
  Gson gson;

  @Inject
  OkHttpClient okHttpClient;

  @Inject
  SharedPreferences sharedPreferences;

  @Inject
  Retrofit retrofit;

  @Inject
  Application application;

  @Inject
  @Named(Activity_Name)
  String name;

  @Inject
  Ball ball;

  @Bind(R.id.imageView)
  ImageView imageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ButterKnife.bind(this);
    ((MyApp) getApplication()).getApplicationComponent()
        .plus(new MainActivityModule(this))
        .inject(this);
    Log.e(TAG, gson.toString());
    Log.e(TAG, okHttpClient.toString());
    Log.e(TAG, sharedPreferences.toString());
    Log.e(TAG, retrofit.toString());
    Log.e(TAG, retrofit.baseUrl().toString());
    Log.e(TAG, application.toString());
    Log.e(TAG, name);
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
