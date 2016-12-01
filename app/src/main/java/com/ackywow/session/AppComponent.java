package com.ackywow.session;

import com.ackywow.session.base.module.SchedulerModule;
import com.ackywow.session.base.scope.ApplicationScope;
import com.ackywow.session.data.db.DBModule;
import com.ackywow.session.data.net.NetModule;
import com.ackywow.session.data.sp.SPModule;
import com.ackywow.session.data.user.UserComponent;
import com.ackywow.session.data.user.UserModule;
import dagger.Component;

/**
 * Created by AckywOw on 2016/6/5.
 */
@ApplicationScope
@Component(
    modules = {
        AppModule.class, SchedulerModule.class, NetModule.class, DBModule.class, SPModule.class
    })
public interface AppComponent {

  UserComponent newUserComponent(UserModule userModule);
}
