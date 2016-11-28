package com.ackywow.session.data;

import android.app.Application;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.data.source.TasksDataSource;
import com.ackywow.session.data.source.TasksRepository;
import com.ackywow.session.data.source.local.TasksLocalDataSource;
import com.ackywow.session.data.source.remote.TasksRemoteDataSource;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jiang on 2016/11/21.
 */
@Module
public class TasksDataSourceModule {

  @Provides
  @Singleton
  static TasksDataSource provideTasksRepository(Application application,
      BaseSchedulerProvider schedulerProvider) {
    checkNotNull(application);
    return TasksRepository.getInstance(TasksRemoteDataSource.getInstance(),
        TasksLocalDataSource.getInstance(application, schedulerProvider));
  }
}
