package com.ackywow.daggersession;

import android.app.Application;
import android.widget.Toast;
import com.ackywow.daggersession.data.source.TasksRepository;
import javax.inject.Inject;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

  @Inject
  Application application;
  @Inject
  TasksRepository tasksRepository;
  private ApplicationComponent applicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);

    Toast.makeText(this, application.toString(), Toast.LENGTH_SHORT).show();
  }

  public ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  public MyApp getApplication() {
    return (MyApp) application;
  }
}
