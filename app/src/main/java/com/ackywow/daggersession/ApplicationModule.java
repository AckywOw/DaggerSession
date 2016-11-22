package com.ackywow.daggersession;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class ApplicationModule {

  Application application;

  public ApplicationModule(MyApp application) {
    this.application = application;
  }

  @Provides
  @Singleton
  public Application getApplication() {
    return application;
  }
}
