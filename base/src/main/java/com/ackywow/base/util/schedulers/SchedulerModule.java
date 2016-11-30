package com.ackywow.base.util.schedulers;

import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import rx.Scheduler;

/**
 * Created by Jiang on 2016/11/21.
 */
@Module
public class SchedulerModule {
  public static final String COMPUTATION = "Computation";
  public static final String IO = "io";
  public static final String UI = "ui";
  public static final String IMMEDIATE = "immediate";

  @Provides
  @Singleton
  static BaseSchedulerProvider provideSchedulerProvider() {
    return new SchedulerProvider();
  }

  @Provides
  @Singleton
  @Named(COMPUTATION)
  static Scheduler provideSchedulerComputation(BaseSchedulerProvider provider) {
    return provider.computation();
  }

  @Provides
  @Singleton
  @Named(IO)
  static Scheduler provideSchedulerIO(BaseSchedulerProvider provider) {
    return provider.io();
  }

  @Provides
  @Singleton
  @Named(UI)
  static Scheduler provideSchedulerUI(BaseSchedulerProvider provider) {
    return provider.ui();
  }

  @Provides
  @Singleton
  @Named(IMMEDIATE)
  static Scheduler provideSchedulerImmediate(BaseSchedulerProvider provider) {
    return provider.immediate();
  }
}
