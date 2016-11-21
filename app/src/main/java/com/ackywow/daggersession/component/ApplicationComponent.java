package com.ackywow.daggersession.component;

import com.ackywow.base.scope.ApplicationScope;
import com.ackywow.daggersession.MyApp;
import com.ackywow.daggersession.module.NetModule;
import com.ackywow.daggersession.ui.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Singleton
@ApplicationScope
@Component(modules = { NetModule.class })
public interface ApplicationComponent {

  void inject(MyApp app);

  void inject(MainActivity app);
}
