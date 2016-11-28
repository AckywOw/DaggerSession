package com.ackywow.session;

import com.ackywow.base.scope.ApplicationScope;
import com.ackywow.base.util.schedulers.SchedulerModule;
import com.ackywow.session.data.TasksDataSourceModule;
import com.ackywow.session.db.DBModule;
import com.ackywow.session.main.MainActivityComponent;
import com.ackywow.session.main.MainActivityModule;
import com.ackywow.session.net.NetModule;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Singleton
@ApplicationScope
@Component(
    modules = {
        NetModule.class, DBModule.class, ApplicationModule.class, SchedulerModule.class,
        TasksDataSourceModule.class
    })
public interface ApplicationComponent {

  void inject(MyApp app);

  MainActivityComponent plus(MainActivityModule module);
}
