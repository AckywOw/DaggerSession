package com.ackywow.daggersession;

import com.ackywow.base.scope.ApplicationScope;
import com.ackywow.base.util.schedulers.SchedulerModule;
import com.ackywow.daggersession.data.TasksDataSourceModule;
import com.ackywow.daggersession.main.MainActivityComponent;
import com.ackywow.daggersession.main.MainActivityModule;
import com.ackywow.daggersession.net.NetModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Singleton
@ApplicationScope
@Component(
    modules = {
        NetModule.class, ApplicationModule.class, SchedulerModule.class, TasksDataSourceModule.class
    })
public interface ApplicationComponent {

  void inject(MyApp app);

  MainActivityComponent plus(MainActivityModule module);
}
