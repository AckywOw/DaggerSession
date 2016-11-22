package com.ackywow.daggersession.data;

import android.app.Application;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.daggersession.data.source.TasksRepository;
import com.ackywow.daggersession.data.source.local.TasksLocalDataSource;
import com.ackywow.daggersession.data.source.remote.TasksRemoteDataSource;
import dagger.Module;
import dagger.Provides;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jiang on 2016/11/21.
 */
@Module
public class TasksRepositoryModule {

  @Provides
  static TasksRepository provideTasksRepository(Application application,
      BaseSchedulerProvider schedulerProvider) {
    checkNotNull(application);
    return TasksRepository.getInstance(TasksRemoteDataSource.getInstance(),
        TasksLocalDataSource.getInstance(application, schedulerProvider));
  }
}
