package com.ackywow.session.base.module;

import android.app.Activity;
import com.ackywow.session.base.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Jiang on 2016/12/1.
 */
@Module
public class ActivityModule {
  private Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = activity;
  }

  @Provides
  @ActivityScope
  public Activity provideActivity() {
    return activity;
  }
}
