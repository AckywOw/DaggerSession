package com.ackywow.session.base.module;

import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.base.util.schedulers.SchedulerProvider;
import com.ackywow.session.base.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import javax.inject.Named;

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
  @ApplicationScope
  static BaseSchedulerProvider provideSchedulerProvider() {
    return new SchedulerProvider();
  }

  @Provides
  @ApplicationScope
  @Named(COMPUTATION)
  static Scheduler provideSchedulerComputation(BaseSchedulerProvider provider) {
    return provider.computation();
  }

  @Provides
  @ApplicationScope
  @Named(IO)
  static Scheduler provideSchedulerIO(BaseSchedulerProvider provider) {
    return provider.io();
  }

  @Provides
  @ApplicationScope
  @Named(UI)
  static Scheduler provideSchedulerUI(BaseSchedulerProvider provider) {
    return provider.ui();
  }
}
