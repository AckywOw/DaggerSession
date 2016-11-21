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
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class MainActivity extends BaseActivity {

  @Inject
  OkHttpClient okHttpClient;

  @Inject
  SharedPreferences sharedPreferences;

  @Inject
  Retrofit retrofit;

  @Inject
  Application application;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ((MyApp) getApplication()).getApplicationComponent().inject(this);
    Log.e(TAG, okHttpClient.toString());
    Log.e(TAG, sharedPreferences.toString());
    Log.e(TAG, retrofit.toString());
    Log.e(TAG, application.toString());
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
