package com.ackywow.daggersession.main;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static com.ackywow.daggersession.constant.Nameds.Activity_Name;

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
  @MainActivityScope
  MainActivity provideMainActivity() {
    return activity;
  }

  @Provides
  @MainActivityScope
  @Named(Activity_Name)
  String provideName() {
    return activity.getComponentName()
                   .toString();
  }
}
