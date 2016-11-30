package com.ackywow.session.main;

import com.ackywow.session.base.scope.ActivityScope;
import dagger.Subcomponent;

/**
 * Created by Jiang on 2016/11/21.
 */
@ActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

  void inject(MainActivity activity);
}
