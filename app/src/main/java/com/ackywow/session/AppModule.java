package com.ackywow.session;

import android.app.Application;
import com.ackywow.session.base.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class AppModule {

  Application application;

  public AppModule(App application) {
    this.application = application;
  }

  @Provides
  @ApplicationScope
  public Application provideApplication() {
    return application;
  }
}
