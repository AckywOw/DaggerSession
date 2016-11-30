package com.ackywow.session;

import com.ackywow.base.util.schedulers.SchedulerModule;
import com.ackywow.session.base.scope.ApplicationScope;
import com.ackywow.session.data.db.DBModule;
import com.ackywow.session.data.net.NetModule;
import com.ackywow.session.data.sp.SPModule;
import com.ackywow.session.main.MainActivityComponent;
import com.ackywow.session.main.MainActivityModule;
import com.ackywow.session.mvp.MvpComponet;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Singleton
@ApplicationScope
@Component(
    modules = {
        ApplicationModule.class, SchedulerModule.class, NetModule.class, DBModule.class,
        SPModule.class
    })
public interface ApplicationComponent {

  MvpComponet plusMvpComponet();

  MainActivityComponent plusMainActivityComponent(MainActivityModule mainActivityModule);
}
