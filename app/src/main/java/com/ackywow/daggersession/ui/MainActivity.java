package com.ackywow.daggersession.ui;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import com.ackywow.base.BaseActivity;
import com.ackywow.base.CommonPresenter;
import com.ackywow.daggersession.MyApp;
import com.ackywow.daggersession.R;
import com.ackywow.daggersession.inject.Ball;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Named;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
  @Named("Activity_Name")
  String name;

  @Inject
  Ball ball;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
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
}
