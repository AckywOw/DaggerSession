package com.ackywow.daggersession;

import com.ackywow.base.scope.ApplicationScope;
import com.ackywow.base.util.schedulers.SchedulerModule;
import com.ackywow.daggersession.data.TasksRepositoryModule;
import com.ackywow.daggersession.module.NetModule;
import com.ackywow.daggersession.ui.MainActivityComponent;
import com.ackywow.daggersession.ui.MainActivityModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Singleton
@ApplicationScope
@Component(
    modules = {
        NetModule.class, ApplicationModule.class, SchedulerModule.class, TasksRepositoryModule.class
    })
public interface ApplicationComponent {

  void inject(MyApp app);

  MainActivityComponent plus(MainActivityModule module);
}
