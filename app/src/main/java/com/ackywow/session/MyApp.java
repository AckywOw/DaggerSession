package com.ackywow.session;

import android.app.Application;
import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.data.source.TasksDataSource;
import com.ackywow.session.db.DaoSession;
import com.ackywow.session.net.ApiService;
import com.ackywow.session.net.RequestUtil;
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
  @Inject
  Lazy<ApiService> apiServiceLazy;
  @Inject
  Lazy<RequestUtil> requestUtilLazy;
  @Inject
  Lazy<DaoSession> daoSessionLazy;

  private ApplicationComponent applicationComponent;

  public static MyApp getApplication() {
    return (MyApp) application;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    applicationComponent = DaggerApplicationComponent.builder()
                                                     .applicationModule(new ApplicationModule(this))
                                                     .build();
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

  @NonNull
  public final ApiService getApiService() {
    return apiServiceLazy.get();
  }

  @NonNull
  public final RequestUtil getRequestUtil() {
    return requestUtilLazy.get();
  }

  @NonNull
  public final DaoSession getDaoSession() {
    return daoSessionLazy.get();
  }
}
