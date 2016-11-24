package com.ackywow.daggersession;

import android.app.Application;
import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.daggersession.data.source.TasksDataSource;
import dagger.Lazy;
import javax.inject.Inject;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

  static Application application;
  @Inject
  Lazy<TasksDataSource> tasksDataSourceLazy;
  @Inject
  Lazy<BaseSchedulerProvider> schedulerProviderLazy;
  private ApplicationComponent applicationComponent;

  public static MyApp getApplication() {
    return (MyApp) application;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  @NonNull
  public final TasksDataSource getTasksDataSource() {
    return tasksDataSourceLazy.get();
  }

  @NonNull
  public final BaseSchedulerProvider getSchedulerProvider() {
    return schedulerProviderLazy.get();
  }
}
