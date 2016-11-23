package com.ackywow.daggersession;

import android.app.Application;
import android.widget.Toast;
import com.ackywow.base.util.schedulers.SchedulerProvider;
import com.ackywow.daggersession.data.source.TasksDataSource;
import javax.inject.Inject;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

  static Application application;
  @Inject
  TasksDataSource tasksDataSource;
  @Inject
  SchedulerProvider schedulerProvider;
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

    Toast.makeText(this, application.toString(), Toast.LENGTH_SHORT).show();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public TasksDataSource getTasksDataSource() {
    return tasksDataSource;
  }

  public SchedulerProvider getSchedulerProvider() {
    return schedulerProvider;
  }
}
