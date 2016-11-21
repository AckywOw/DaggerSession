package com.ackywow.daggersession.module;

import android.app.Application;
import com.ackywow.daggersession.MyApp;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class AppModule {

  Application application;

  public AppModule(MyApp application) {
    this.application = application;
  }

  @Provides
  @Singleton
  public Application getApplication() {
    return application;
  }
}
