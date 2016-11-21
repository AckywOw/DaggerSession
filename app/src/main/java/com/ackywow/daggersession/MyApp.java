package com.ackywow.daggersession;

import android.app.Application;
import android.widget.Toast;
import com.ackywow.daggersession.component.ApplicationComponent;
import com.ackywow.daggersession.component.DaggerApplicationComponent;
import com.ackywow.daggersession.module.AppModule;
import javax.inject.Inject;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

  @Inject
  Application application;
  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    applicationComponent = DaggerApplicationComponent.builder()
        .appModule(new AppModule(this))
        .build();

    applicationComponent.inject(this);

    Toast.makeText(this, application.toString(), Toast.LENGTH_SHORT).show();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public MyApp getApplication() {
    return (MyApp) application;
  }
}
