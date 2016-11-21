package com.ackywow.daggersession;

import android.app.Application;
import com.ackywow.daggersession.component.ApplicationComponent;
import com.ackywow.daggersession.component.DaggerApplicationComponent;
import com.ackywow.daggersession.module.AppModule;
import com.ackywow.daggersession.module.NetModule;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

  private ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.builder()
        .appModule(new AppModule(this))
        .netModule(new NetModule("https://app.ackywow.com"))
        .build();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
