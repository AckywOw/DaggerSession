package com.ackywow.session.main;

import com.ackywow.session.base.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.Activity_Name;

/**
 * Created by Jiang on 2016/11/21.
 */
@Module
public class MainActivityModule {

  private MainActivity activity;

  public MainActivityModule(MainActivity activity) {
    this.activity = activity;
  }

  @Provides
  @ActivityScope
  MainActivity provideMainActivity() {
    return activity;
  }

  @Provides
  @ActivityScope
  @Named(Activity_Name)
  String provideName() {
    return activity.getComponentName()
                   .getClassName();
  }
}
