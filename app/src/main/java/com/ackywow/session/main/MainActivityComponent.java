package com.ackywow.session.main;

import com.ackywow.session.base.scope.ActivityScope;
import dagger.Component;

/**
 * Created by Jiang on 2016/11/21.
 */
@ActivityScope
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {
  void inject(MainActivity mainActivity);
}
