package com.ackywow.session;

import android.app.Application;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

  private static MyApp application;
  private ApplicationComponent applicationComponent;

  public static MyApp getApplication() {
    return application;
  }

  public static ApplicationComponent getApplicationComponent() {
    return getApplication().applicationComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    applicationComponent = DaggerApplicationComponent.builder()
                                                     .applicationModule(new ApplicationModule(this))
                                                     .build();
  }
}
